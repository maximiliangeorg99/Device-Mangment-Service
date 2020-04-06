package c24.thriftshop.stripe.demo.persistence;

import c24.thriftshop.stripe.demo.presentation.Customer;
import c24.thriftshop.stripe.demo.presentation.Customers;

public interface CustomerDao {
    public Customer getCustomer();

    public Customers getAllCustomer();

    public boolean deleteCustomer();

    public Customer createCustomer();

    public Customer updateCustomer();

}
