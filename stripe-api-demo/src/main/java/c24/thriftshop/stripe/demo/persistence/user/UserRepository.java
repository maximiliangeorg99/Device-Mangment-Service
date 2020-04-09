package c24.thriftshop.stripe.demo.persistence.user;

import c24.thriftshop.stripe.demo.persistence.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    public Optional<UserEntity> findByEmail(String email);

    public void softDelete(final UserEntity entity);
}
