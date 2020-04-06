package c24.thriftshop.stripe.demo.domain;

import c24.thriftshop.stripe.demo.presentation.Customer;
import c24.thriftshop.stripe.demo.presentation.Customers;

public interface CustomerDao {
     Customer getCustomerById(String id);

     Customers getAllCustomer();

     boolean deleteCustomerById(String id);

     Customer createCustomer(Customer customer);

     Customer updateCustomerById(Customer customer, String id);
}
