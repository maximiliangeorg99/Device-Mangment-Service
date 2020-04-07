package c24.thriftshop.stripe.demo.persistence.customer;

import c24.thriftshop.stripe.demo.persistence.CrudRepository;

public interface CustomerRepository extends CrudRepository<StripeCustomer, String> {

}