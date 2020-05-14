package persistence;

import entity.DeviceEntity;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends CrudRepository<DeviceEntity, UUID> {
    Optional<DeviceEntity> findByDeviceName(final String name);

    Optional<DeviceEntity> findByDeviceNameAndDeviceId(final String name, final int id);

    int countByDeviceName(final String name);

    ArrayList<DeviceEntity> findAllByDeviceName(final String name);
}
