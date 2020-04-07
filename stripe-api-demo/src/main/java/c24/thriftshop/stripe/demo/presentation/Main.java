package c24.thriftshop.stripe.demo.presentation;

import c24.thriftshop.stripe.demo.domain.Customer;

public class Main {
    public static void main(final String[] args) {
        final Customer customer = new Customer("rc_1GVBOr2eZvKYlo2C9CVtTBiW", "Max@Web.de", "Max", "1234567890", 1000, "eur");
        final CustomerModel customerModel = new CustomerModel(customer);
        System.out.println(customerModel);
    }
}
