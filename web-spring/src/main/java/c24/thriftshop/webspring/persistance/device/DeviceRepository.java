package c24.thriftshop.webspring.persistance.device;

import c24.thriftshop.webspring.persistance.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends CrudRepository<DeviceEntity, UUID> {

    public Optional<DeviceEntity> findByName(String deviceName);

    public void updateAvailable(final String entity, boolean b);
}

