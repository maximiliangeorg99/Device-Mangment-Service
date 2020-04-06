package c24.thriftshop.stripe.demo.presentation;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Customers {
    @SerializedName("data")
    List<Customer> list;

    @Override
    public String toString() {
        return Arrays.toString(list.toArray());
    }
}
