package c24.thriftshop.webspring.domain.device.rent;

import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationRequest;
import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationService;
import c24.thriftshop.webspring.persistence.device.DeviceEntity;
import c24.thriftshop.webspring.persistence.device.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class RentService {
    private final DeviceRepository deviceRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public RentService(final DeviceRepository deviceRepository,
                       final AuthenticationService authenticationService) {
        this.deviceRepository = deviceRepository;
        this.authenticationService = authenticationService;
    }

    private Date addHours(final Date date, final int hours) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    private RentResponse bookDevice(final DeviceEntity deviceEntity, final RentRequest request) {
        if (authenticationService.execute(new AuthenticationRequest(request.getUsername())).isSuccessful()) {
            deviceEntity.setAvailable(false);
            deviceEntity.setRentDate(new Date());
            deviceEntity.setReturnDate(addHours(new Date(), request.getDurationInDays()));
            deviceRepository.save(deviceEntity);
            return new RentResponse(RentMessage.SUCCESSFUL, true, deviceEntity.getReturnDate());
        }
        return new RentResponse(RentMessage.NOT_AUTHENTICATED, false, deviceEntity.getReturnDate());
    }

    public RentResponse execute(final RentRequest rentRequest) {

        final Optional<DeviceEntity> optionalDeviceEntity = deviceRepository.findByName(rentRequest.getDeviceName());
        if (optionalDeviceEntity.isPresent()) {
            if (optionalDeviceEntity.get().isAvailable())
                return bookDevice(optionalDeviceEntity.get(), rentRequest);
            else
                return new RentResponse(RentMessage.IN_RENT, false, optionalDeviceEntity.get().getReturnDate());
        } else
            return new RentResponse(RentMessage.NO_SUCH_DEVICE, false, null);
    }
}
