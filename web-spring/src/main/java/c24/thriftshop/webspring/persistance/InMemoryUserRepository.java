package c24.thriftshop.webspring.persistance;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository("InMemoryDB")
public class InMemoryUserRepository implements UserRepository {
    private static final ArrayList<UserEntity> DB = new ArrayList<>();

    @Override
    public Optional<UserEntity> findByEmail(final String email) {
        final Optional<UserEntity> optional;
        for (final UserEntity userEntity : DB) {
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
        for (final UserEntity userEntity : DB) {
            if (userEntity.getId().equals(entity.getId())) {
                userEntity.setActive(false);
                return;
            }
        }
    }

    @Override
    public Optional<UserEntity> findById(final UUID uuid) {
        final Optional<UserEntity> optional;
        for (final UserEntity userEntity : DB) {
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
        return DB;
    }

    @Override
    public UserEntity save(final UserEntity entity) {
        if (!DB.contains(entity))
            DB.add(entity);
        return entity;
    }

    @Override
    public void delete(final UserEntity entity) {
        DB.remove(entity);
    }
}