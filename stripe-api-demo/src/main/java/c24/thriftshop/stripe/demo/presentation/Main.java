package c24.thriftshop.stripe.demo.presentation;

import c24.thriftshop.stripe.demo.domain.Customer.CustomerService;
import c24.thriftshop.stripe.demo.persistence.Customer.StripeCustomerRepository;
import c24.thriftshop.stripe.demo.presentation.Customer.CustomerModel;

public class Main {
    public static void main(final String[] args) {
        final StripeCustomerRepository stripeCustomerRepository = new StripeCustomerRepository();
        final CustomerService customerService = new CustomerService(stripeCustomerRepository);
        final CustomerModel customerModel = new CustomerModel(customerService.getCustomerById("cus_H3KLdrbHbDMhPE"));
        System.out.println(customerModel);
    }
}
