package c24.thriftshop.webspring.domain.device.rent;

import c24.thriftshop.webspring.domain.device.Device;
import c24.thriftshop.webspring.persistence.device.DeviceEntity;
import c24.thriftshop.webspring.persistence.device.DeviceRepository;
import c24.thriftshop.webspring.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentService {
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;

    @Autowired
    public RentService(final DeviceRepository deviceRepository,
                       final UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    private RentResponse bookDevice(final String deviceName, final String email, final String token, final int duration) {
        if (userRepository.findByEmail(email).filter(userEntity -> userEntity.getToken().toString().equals(token)).isPresent()) {
            final Device device = new Device(deviceName, email, duration);
            deviceRepository.save(new DeviceEntity());
            return new RentResponse(RentMessage.SUCCESSFUL, true, null);
        }
        return new RentResponse(RentMessage.NOT_AUTHENTICATED, false, null);
    }

    public RentResponse execute(final RentRequest rentRequest) {

        final Optional<DeviceEntity> optionalDeviceEntity = deviceRepository.findByName(rentRequest.getDeviceName());
        if (optionalDeviceEntity.isPresent()) {
            if (optionalDeviceEntity.get().isAvailable())
                return bookDevice(rentRequest.getDeviceName(), rentRequest.getEmail(), rentRequest.getToken(), rentRequest.getDurationInDays());
            else
                return new RentResponse(RentMessage.IN_RENT, false, optionalDeviceEntity.get().getReturnDate());
        } else
            return new RentResponse(RentMessage.NO_SUCH_DEVICE, false, null);
    }
}
