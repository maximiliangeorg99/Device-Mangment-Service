package c24.thriftshop.stripe.demo.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

public class Customers {
    @SerializedName("data")
    List<Customer> list;

    public Customers(List<Customer> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return Arrays.toString(list.toArray());
    }
}
