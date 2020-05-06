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
        final DeviceEntity newDeviceEntity = new DeviceEntity(UUID.randomUUID(), addrequest.name, true, "", null, null);
        deviceRepository.save(newDeviceEntity);
    }
}
