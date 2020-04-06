package c24.thriftshop.stripe.demo.domain;

import c24.thriftshop.stripe.demo.presentation.Customer;
import c24.thriftshop.stripe.demo.presentation.Customers;

public interface Service {
    public Customer getCustomer();

    public Customers getAllCustomer();

    public boolean deleteCustomer();

    public Customer createCustomer();

    public Customer updateCustomer();
}
