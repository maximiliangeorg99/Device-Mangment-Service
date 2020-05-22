package c24.thriftshop.webjavalin.persistence;

import c24.thriftshop.webjavalin.entity.DeviceEntity;

import java.util.ArrayList;
import java.util.Optional;

//TODO JDBC Template prepared statement
//TODO Speedment
//TODO Hibernate
public interface DeviceRepository extends CrudRepository<DeviceEntity, Integer> {
    Optional<DeviceEntity> findByDeviceName(final String name);

    Optional<DeviceEntity> findByDeviceNameAndDeviceId(final String name, final int id);

    int countByDeviceName(final String name);

    ArrayList<DeviceEntity> findAllByDeviceName(final String name);
}
