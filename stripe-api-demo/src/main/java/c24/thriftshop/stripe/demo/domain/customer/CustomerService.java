package c24.thriftshop.stripe.demo.domain.customer;

import c24.thriftshop.stripe.demo.persistence.customer.CustomerRepository;
import c24.thriftshop.stripe.demo.persistence.customer.StripeCustomer;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(final String id) {
        //To-Do is present check and handling
        return new Customer(customerRepository.findById(id).get());
    }

    public Customers getAllCustomer() {
        return new Customers(customerRepository.findAll());
    }

    public void deleteCustomerById(final Customer customer) {
        customerRepository.delete(new StripeCustomer(customer));
    }

    public Customer createCustomer(final Customer customer) {
        return new Customer(customerRepository.save(new StripeCustomer(customer)));
    }
}
