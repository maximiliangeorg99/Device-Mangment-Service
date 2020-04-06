package c24.thriftshop.stripe.demo.persistence;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StripeCustomers {
    @SerializedName("data")
    List<StripeCustomer> list;
}
