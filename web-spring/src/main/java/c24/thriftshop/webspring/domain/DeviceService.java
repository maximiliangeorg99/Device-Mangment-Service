package c24.thriftshop.webspring.domain;

import c24.thriftshop.webspring.persistance.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(@Qualifier("devicePostgres") final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    //TODO implement
    public DeviceState rentDevice() {
        return null;
    }

    //TODO implement
    public DeviceState returnDevice() {
        return null;
    }
}
