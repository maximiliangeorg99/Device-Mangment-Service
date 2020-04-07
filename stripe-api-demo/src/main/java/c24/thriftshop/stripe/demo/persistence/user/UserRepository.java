package c24.thriftshop.stripe.demo.persistence.user;

import c24.thriftshop.stripe.demo.persistence.CrudRepository;

public interface UserRepository extends CrudRepository<JsonUser, String> {
}
