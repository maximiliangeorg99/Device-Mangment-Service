package c24.thriftshop.stripe.demo.persistence.Customer;

import c24.thriftshop.stripe.demo.persistence.CrudRepository;

public interface CustomerRepository extends CrudRepository<StripeCustomer, String> {

}