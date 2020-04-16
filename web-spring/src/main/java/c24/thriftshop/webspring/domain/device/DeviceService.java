package c24.thriftshop.webspring.domain.device;

import c24.thriftshop.webspring.persistence.device.DeviceEntity;
import c24.thriftshop.webspring.persistence.device.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(@Qualifier("Device") final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    private DeviceState bookDevice(final String deviceName, final String email, final int duration) {
        final Device device = new Device(deviceName, email, duration);
        deviceRepository.save(new DeviceEntity(device));
        return DeviceState.Available;
    }

    public DeviceState rentDevice(final String deviceName, final String email, final int duration) {
        return deviceRepository.findByName(deviceName)
                .filter(DeviceEntity::isAvailable)
                .map(deviceEntity -> bookDevice(deviceName, email, duration))
                .orElse(DeviceState.NotAvailable);
    }

    public DeviceState returnDevice(final String deviceName, final String email) {
        final Optional<DeviceEntity> optionalDevice = deviceRepository.findByName(deviceName);
        if (optionalDevice.isPresent() && optionalDevice.get().getEmail().equals(email)) {
            deviceRepository.updateAvailable(deviceName, true);
            return DeviceState.Available;
        }
        return DeviceState.DontOwnDevice;
    }
}
