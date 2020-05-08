package c24.thriftshop.webspring.domain.device.rent;

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

    private RentResponse bookDevice(final DeviceEntity deviceEntity,
                                    final RentRequest request, final String userId) {
        if (!deviceEntity.isAvailable()) {
            return new RentResponse("In rent until " + deviceEntity.getReturnDate().toString(), false);
        }
        deviceEntity.setAvailable(false);
        deviceEntity.setUserId(userId);
        deviceEntity.setRentDate(new Date());
        deviceEntity.setReturnDate(addHours(new Date(), request.getDurationInHours()));
        deviceRepository.save(deviceEntity);
        return new RentResponse("Successful", true);
    }

    public RentResponse execute(final RentRequest rentRequest, final String userId) {
        final int deviceCount = deviceRepository.countByDeviceName(rentRequest.getDeviceName());
        if (deviceCount == 0) {
            return new RentResponse("No such device", false);
        } else if (deviceCount == 1) {
            final Optional<DeviceEntity> optionalDeviceEntity = deviceRepository.findByDeviceName(rentRequest.getDeviceName());
            return bookDevice(optionalDeviceEntity.get(), rentRequest, userId);
        } else {
            if (rentRequest.getDeviceId() == 0) {
                return new RentResponse("There are multiple Devices with this name pleas add the DeviceId", false);
            } else {
                final Optional<DeviceEntity> optionalDeviceEntity = deviceRepository.findByDeviceNameAndDeviceId(rentRequest.getDeviceName(), rentRequest.getDeviceId());
                if (optionalDeviceEntity.isPresent()) {
                    return bookDevice(optionalDeviceEntity.get(), rentRequest, userId);
                } else {
                    return new RentResponse("No device with this device name and device userId", false);
                }
            }
        }
    }
}
