package c24.thriftshop.webjavalin.persistence;

import c24.thriftshop.webjavalin.entity.DeviceEntity;
import com.google.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateRepository implements DeviceRepository {
    private final EntityManager entityManager;

    @Inject
    public HibernateRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<DeviceEntity> findByDeviceName(final String name) {
        EntityTransaction et = null;
        final DeviceEntity deviceEntity = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            entityManager.createQuery("SELECT device from DeviceEntity device where device.deviceName = ?1")
                    .setParameter(1, name)
                    .getFirstResult();
            et.commit();
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(deviceEntity);
    }

    @Override
    public Optional<DeviceEntity> findByDeviceNameAndDeviceId(final String name, final int id) {
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            final Optional<DeviceEntity> result = entityManager.createQuery("SELECT device from DeviceEntity device where device.deviceName = ?1 and device.deviceId = ?2")
                    .setParameter(1, name)
                    .setParameter(2, id)
                    .getResultStream().findFirst();
            et.commit();
            return result;
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public int countByDeviceName(final String name) {
        return findAllByDeviceName(name).size();
    }

    @Override
    public ArrayList<DeviceEntity> findAllByDeviceName(final String name) {
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            final ArrayList<DeviceEntity> devices = new ArrayList<>(entityManager.createQuery("SELECT device from DeviceEntity device where device.deviceName = ?1")
                    .setParameter(1, name)
                    .getResultList());
            et.commit();
            return devices;
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public long count() {
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            final List<DeviceEntity> devices = entityManager.createQuery("SELECT device from DeviceEntity device").getResultList();
            et.commit();
            return devices.size();
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return 0;
    }

    @Override
    public void delete(final DeviceEntity entity) {
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            entityManager.remove(entity);
            et.commit();
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteById(final Integer id) {
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            final DeviceEntity deviceEntity = entityManager.find(DeviceEntity.class, id);
            entityManager.remove(deviceEntity);
            et.commit();
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean existsById(final Integer id) {
        EntityTransaction et = null;
        boolean b = false;
        try {
            et = entityManager.getTransaction();
            et.begin();
            b = entityManager.find(DeviceEntity.class, id).isAvailable();
            et.commit();
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return b;
    }

    @Override
    public Iterable<DeviceEntity> findAll() {
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            final List<DeviceEntity> devices = entityManager.createQuery("SELECT device from DeviceEntity device").getResultList();
            et.commit();
            return devices;
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public Optional<DeviceEntity> findById(final Integer id) {
        EntityTransaction et = null;
        DeviceEntity deviceEntity = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            deviceEntity = entityManager.find(DeviceEntity.class, id);
            entityManager.detach(deviceEntity);
            et.commit();
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(deviceEntity);
    }

    @Override
    public DeviceEntity save(final DeviceEntity entity) {
        EntityTransaction et = null;
        try {
            et = entityManager.getTransaction();
            et.begin();
            entityManager.persist(entity);
            et.commit();
        } catch (final Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
        return entity;
    }
}
