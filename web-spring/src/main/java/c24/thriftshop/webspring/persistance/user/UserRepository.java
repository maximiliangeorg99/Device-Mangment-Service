package c24.thriftshop.webspring.persistance.user;

import c24.thriftshop.webspring.persistance.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    public Optional<UserEntity> findByEmail(String email);

    public void softDelete(final UserEntity entity);
}
