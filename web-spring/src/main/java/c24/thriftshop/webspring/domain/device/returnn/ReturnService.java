package c24.thriftshop.webspring.domain.device.returnn;

import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationRequest;
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
        if (authenticationService.execute(new AuthenticationRequest(deviceEntity.getUsername())).isSuccessful()) {
            deviceEntity.setAvailable(true);
            deviceRepository.save(deviceEntity);
            return new ReturnResponse(ReturnMessage.SUCCESSFUL);
        }
        return new ReturnResponse(ReturnMessage.NOT_AUTHENTICATED);
    }

    public ReturnResponse execute(final ReturnRequest returnRequest) {
        return deviceRepository.findByName(returnRequest.getDeviceName())
                .filter(deviceEntity -> deviceEntity.getUsername().equals(returnRequest.getUsername()))
                .map(this::bookDevice)
                .orElse(new ReturnResponse(ReturnMessage.NOT_IN_RENT_BY_THIS_USER));
    }
}
