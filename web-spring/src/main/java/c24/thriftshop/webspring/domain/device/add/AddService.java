package c24.thriftshop.webspring.domain.device.add;

import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationService;
import c24.thriftshop.webspring.persistence.device.DeviceEntity;
import c24.thriftshop.webspring.persistence.device.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddService {
    private final DeviceRepository deviceRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public AddService(final DeviceRepository deviceRepository,
                      final AuthenticationService authenticationService) {
        this.deviceRepository = deviceRepository;
        this.authenticationService = authenticationService;
    }

    public void execute(final Addrequest addrequest) {
        final DeviceEntity newDeviceEntity = new DeviceEntity();
        newDeviceEntity.setId(UUID.randomUUID());
        newDeviceEntity.setAvailable(true);
        newDeviceEntity.setUserId("");
        newDeviceEntity.setDeviceName(addrequest.getName());
        newDeviceEntity.setDeviceDescription(addrequest.description);
        //NOT THREAD SAFE!
        final int devicesCount = deviceRepository.countByDeviceName(addrequest.getName());
        newDeviceEntity.setDeviceId(devicesCount + 1);
        deviceRepository.save(newDeviceEntity);
    }
}
