package c24.thriftshop.webspring.domain.device.returnn;

import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationService;
import c24.thriftshop.webspring.persistence.device.DeviceEntity;
import c24.thriftshop.webspring.persistence.device.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnService {
    private final DeviceRepository deviceRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public ReturnService(final DeviceRepository deviceRepository,
                         final AuthenticationService authenticationService) {
        this.deviceRepository = deviceRepository;
        this.authenticationService = authenticationService;
    }

    private ReturnResponse bookDevice(final DeviceEntity deviceEntity) {
        deviceEntity.setAvailable(true);
        deviceEntity.setUserId("");
        deviceEntity.setRentDate(null);
        deviceEntity.setReturnDate(null);
        deviceRepository.save(deviceEntity);
        return new ReturnResponse("Successful", true);
    }

    public ReturnResponse execute(final ReturnRequest returnRequest, final java.lang.String id) {
        return deviceRepository.findByName(returnRequest.getDeviceName())
                .filter(deviceEntity -> deviceEntity.getUserId().equals(id))
                .map(this::bookDevice)
                .orElse(new ReturnResponse("Not in rent by this User", false));
    }
}
