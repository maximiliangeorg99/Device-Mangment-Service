package c24.thriftshop.webspring.persistance.datasource;

import c24.thriftshop.webspring.domain.Device;
import c24.thriftshop.webspring.persistance.CrudRepository;
import c24.thriftshop.webspring.persistance.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends CrudRepository<Device, UUID> {

    public Optional<UserEntity> findByName(String deviceName);

    public void updateAvailable(final Device entity, boolean b);
}

