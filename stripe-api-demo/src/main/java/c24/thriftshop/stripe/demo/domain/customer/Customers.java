package c24.thriftshop.stripe.demo.domain.customer;

import c24.thriftshop.stripe.demo.persistence.customer.StripeCustomer;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Customers {
    @SerializedName("data")
    List<Customer> list;

    public Customers(final Collection<StripeCustomer> stripeCustomers) {
        //To-Do
        list = new ArrayList<>();
        for (final StripeCustomer stripeCustomer : stripeCustomers) {
            final Customer customer = new Customer(stripeCustomer);
            list.add(customer);
        }
    }

    public Customers(final List<Customer> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return Arrays.toString(list.toArray());
    }
}
