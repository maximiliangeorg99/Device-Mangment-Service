package c24.thriftshop.webspring.persistance;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository("devicePostgres")
public class PostgresDeviceRepository implements DeviceRepository {
    //TODO implement
    @Override
    public Optional<UserEntity> findByName(final String deviceName) {
        return Optional.empty();
    }

    @Override
    public void updateAvailable(final DeviceEntity entity, final boolean b) {

    }

    @Override
    public Optional<DeviceEntity> findById(final UUID uuid) {
        return Optional.empty();
    }

    @Override
    public Collection<DeviceEntity> findAll() {
        return null;
    }

    @Override
    public DeviceEntity save(final DeviceEntity entity) {
        return null;
    }

    @Override
    public void delete(final DeviceEntity entity) {

    }
}
