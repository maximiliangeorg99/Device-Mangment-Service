package c24.thriftshop.stripe.demo.domain;

public class DataAccessService {

    private final CustomerDao customerDao;

    public DataAccessService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer getCustomerById(String id) {
        return customerDao.getCustomerById(id);
    }

    public Customers getAllCustomer() {
        return customerDao.getAllCustomer();
    }

    public boolean deleteCustomerById(String id) {
        return customerDao.deleteCustomerById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerDao.createCustomer(customer);
    }

    public Customer updateCustomerById(Customer customer, String id) {
        return customerDao.updateCustomerById(customer, id);
    }
}
