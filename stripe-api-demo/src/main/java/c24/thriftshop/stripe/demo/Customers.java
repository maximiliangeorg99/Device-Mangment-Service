package c24.thriftshop.stripe.demo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customers {
    @SerializedName("data")
    List<Customer> list;
}
