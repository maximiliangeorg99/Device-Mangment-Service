package c24.thriftshop.stripe.demo.persistence;

import c24.thriftshop.stripe.demo.domain.Customer;
import c24.thriftshop.stripe.demo.domain.Customers;

import java.util.Collections;
import java.util.List;

public class CustomerBuilder {
    public Customer makeCustomer(StripeCustomer stripeCustomer) {
        Customer customer = new Customer(stripeCustomer.getId(), stripeCustomer.getEmail(), stripeCustomer.getName(), stripeCustomer.getPhone(), stripeCustomer.getBalance(), stripeCustomer.getCurrency());
        return customer;
    }

    public Customers makeCustomers(StripeCustomers stripeCustomers) {
        List<Customer> list = Collections.EMPTY_LIST;
        for (StripeCustomer stripeCustomer : stripeCustomers.list) {
            list.add(makeCustomer(stripeCustomer));
        }
        Customers customers = new Customers(list);
        return customers;
    }
}