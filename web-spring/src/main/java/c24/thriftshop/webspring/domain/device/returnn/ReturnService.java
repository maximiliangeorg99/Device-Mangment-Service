package c24.thriftshop.webspring.domain.device.returnn;

import c24.thriftshop.webspring.persistence.device.DeviceEntity;
import c24.thriftshop.webspring.persistence.device.DeviceRepository;
import c24.thriftshop.webspring.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnService {
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReturnService(final DeviceRepository deviceRepository, final UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    private ReturnResponse bookDevice(final DeviceEntity deviceEntity, final String token) {
        if (userRepository.findByEmail(deviceEntity.getEmail()).filter(userEntity -> userEntity.getToken().toString().equals(token)).isPresent()) {
            deviceRepository.save(deviceEntity);
            return new ReturnResponse(ReturnMessage.SUCCESSFUL);
        }
        return new ReturnResponse(ReturnMessage.NOT_AUTHENTICATED);
    }

    public ReturnResponse execute(final ReturnRequest returnRequest) {
        return deviceRepository.findByName(returnRequest.getDeviceName())
                .filter(deviceEntity -> deviceEntity.getEmail().equals(returnRequest.getEmail()))
                .map(deviceEntity -> bookDevice(deviceEntity, returnRequest.getToken()))
                .orElse(new ReturnResponse(ReturnMessage.NOT_IN_RENT_BY_THIS_EMAIL));
    }
}
