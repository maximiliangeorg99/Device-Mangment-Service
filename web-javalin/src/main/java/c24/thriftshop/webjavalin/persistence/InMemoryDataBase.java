package c24.thriftshop.webjavalin.persistence;

import c24.thriftshop.webjavalin.entity.DeviceEntity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class InMemoryDataBase implements DeviceRepository {
    ArrayList<DeviceEntity> DB = new ArrayList<>();

    @Inject
    public InMemoryDataBase() {
    }

    @Override
    public long count() {
        return DB.size();
    }

    @Override
    public void delete(final DeviceEntity entity) {
        DB.remove(entity);
    }

    @Override
    public void deleteById(final UUID uuid) {
        for (final DeviceEntity d : DB) {
            if (d.getId().equals(uuid))
                DB.remove(d);
        }
    }

    @Override
    public boolean existsById(final UUID uuid) {
        for (final DeviceEntity d : DB) {
            if (d.getId().equals(uuid))
                return true;
        }
        return false;
    }

    @Override
    public Iterable<DeviceEntity> findAll() {
        return new ArrayList<>(DB);
    }

    @Override
    public Optional<DeviceEntity> findById(final UUID uuid) {
        DeviceEntity deviceEntity = null;
        for (final DeviceEntity d : DB) {
            if (d.getId().equals(uuid))
                deviceEntity = d;
        }
        return Optional.ofNullable(deviceEntity);
    }

    @Override
    public DeviceEntity save(final DeviceEntity entity) {
        if (DB.contains(entity)) {
            DB.remove(entity);
        }
        DB.add(entity);
        return entity;
    }

    @Override
    public Optional<DeviceEntity> findByDeviceName(final String name) {
        DeviceEntity deviceEntity = null;
        for (final DeviceEntity d : DB) {
            if (d.getDeviceName().equals(name))
                deviceEntity = d;
        }
        return Optional.ofNullable(deviceEntity);
    }

    @Override
    public Optional<DeviceEntity> findByDeviceNameAndDeviceId(final String name, final int id) {
        DeviceEntity deviceEntity = null;
        for (final DeviceEntity d : DB) {
            if (d.getDeviceName().equals(name) && d.getDeviceId() == id)
                deviceEntity = d;
        }
        return Optional.ofNullable(deviceEntity);
    }

    @Override
    public int countByDeviceName(final String name) {
        int c = 0;
        for (final DeviceEntity d : DB) {
            if (d.getDeviceName().equals(name))
                c++;
        }
        return c;
    }

    @Override
    public ArrayList<DeviceEntity> findAllByDeviceName(final String name) {
        final ArrayList<DeviceEntity> list = new ArrayList<>();
        for (final DeviceEntity d : DB) {
            if (d.getDeviceName().equals(name))
                list.add(d);
        }
        return list;
    }
}
