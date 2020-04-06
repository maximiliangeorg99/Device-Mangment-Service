package c24.thriftshop.stripe.demo.domain;

import c24.thriftshop.stripe.demo.presentation.Customer;
import c24.thriftshop.stripe.demo.presentation.Customers;

public class DataAccessService implements CustomerDao {

    private final CustomerDao customerDao;

    public DataAccessService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public Customers getAllCustomer() {
        return customerDao.getAllCustomer();
    }

    @Override
    public boolean deleteCustomerById(String id) {
        return customerDao.deleteCustomerById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDao.createCustomer(customer);
    }

    @Override
    public Customer updateCustomerById(Customer customer, String id) {
        return customerDao.updateCustomerById(customer, id);
    }
}
