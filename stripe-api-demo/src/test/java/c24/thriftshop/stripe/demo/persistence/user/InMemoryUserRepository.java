package c24.thriftshop.stripe.demo.persistence.user;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUserRepository implements UserRepository {
    private final UserEntities DB;

    public InMemoryUserRepository() {
        DB = new UserEntities();
    }

    @Override
    public Optional<UserEntity> findByEmail(final String email) {
        final Optional<UserEntity> optional;
        for (final UserEntity userEntity : DB.list) {
            if (userEntity.getEmail().equalsIgnoreCase(email) && userEntity.isActive()) {
                optional = Optional.of(userEntity);
                return optional;
            }
        }
        optional = Optional.empty();
        return optional;
    }

    @Override
    public void softDelete(final UserEntity entity) {
        for (final UserEntity userEntity : DB.list) {
            if (userEntity.getId().equals(entity.getId())) {
                userEntity.setActive(false);
                return;
            }
        }
    }

    @Override
    public Optional<UserEntity> findById(final UUID uuid) {
        final Optional<UserEntity> optional;
        for (final UserEntity userEntity : DB.list) {
            if (userEntity.getId().equals(uuid) && userEntity.isActive()) {
                optional = Optional.of(userEntity);
                return optional;
            }
        }
        optional = Optional.empty();
        return optional;
    }

    @Override
    public Collection<UserEntity> findAll() {
        return DB.list;
    }

    @Override
    public UserEntity save(final UserEntity entity) {
        if (!DB.list.contains(entity))
            DB.list.add(entity);
        return entity;
    }

    @Override
    public void delete(final UserEntity entity) {
        DB.list.remove(entity);
    }
}