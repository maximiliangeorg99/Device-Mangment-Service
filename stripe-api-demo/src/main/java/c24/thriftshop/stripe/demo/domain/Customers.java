package c24.thriftshop.stripe.demo.domain;

import c24.thriftshop.stripe.demo.persistence.StripeCustomer;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Customers {
    @SerializedName("data")
    List<Customer> list;

    public Customers(final Collection<StripeCustomer> stripeCustomers) {
        list = Collections.EMPTY_LIST;
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
