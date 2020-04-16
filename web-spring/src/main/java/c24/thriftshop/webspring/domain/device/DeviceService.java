package c24.thriftshop.webspring.domain.device;

import c24.thriftshop.webspring.persistance.device.DeviceEntity;
import c24.thriftshop.webspring.persistance.device.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(@Qualifier("devicePostgres") final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceState rentDevice(final String deviceName, final String email, final int duration) {
        final Optional<DeviceEntity> optionalDevice = deviceRepository.findByName(deviceName);
        if (optionalDevice.isPresent() && optionalDevice.get().isAvailable()) {
            final Device device = new Device(deviceName, email, duration);
            deviceRepository.save(new DeviceEntity(device));
            return DeviceState.Available;
        }
        return DeviceState.NotAvailable;
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
