package c24.thriftshop.stripe.demo.persistence;

import c24.thriftshop.stripe.demo.presentation.Customer;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class StripeCustomers {
    @SerializedName("data")
    List<Customer> list;
}
