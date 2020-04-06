package c24.thriftshop.stripe.demo.persistence;

import c24.thriftshop.stripe.demo.domain.CustomerDao;
import c24.thriftshop.stripe.demo.domain.Customer;
import c24.thriftshop.stripe.demo.domain.Customers;

public class StripeCustomerDao implements CustomerDao {

    private final CustomerBuilder customerBuilder;
    private final StripeDataAccessService stripeDataAccessService;

    public StripeCustomerDao(CustomerBuilder customerBuilder, StripeDataAccessService stripeDataAccessService) {
        this.customerBuilder = customerBuilder;
        this.stripeDataAccessService = stripeDataAccessService;
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerBuilder.makeCustomer(stripeDataAccessService.getCustomerById(id));
    }

    @Override
    public Customers getAllCustomer() {
        return customerBuilder.makeCustomers(stripeDataAccessService.getAllCustomer());
    }

    @Override
    public boolean deleteCustomerById(String id) {
        return stripeDataAccessService.deleteCustomerById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomerById(Customer customer, String id) {
        return null;
    }
}
