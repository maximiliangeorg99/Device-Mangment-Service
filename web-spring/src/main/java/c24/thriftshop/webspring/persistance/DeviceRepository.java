package c24.thriftshop.webspring.persistance;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends CrudRepository<DeviceEntity, UUID> {

    public Optional<UserEntity> findByName(String deviceName);

    public void updateAvailable(final DeviceEntity entity, boolean b);
}

