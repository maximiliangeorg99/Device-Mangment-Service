package c24.thriftshop.stripe.demo.persistence.user;

import c24.thriftshop.stripe.demo.persistence.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<JsonUser, UUID> {
}
