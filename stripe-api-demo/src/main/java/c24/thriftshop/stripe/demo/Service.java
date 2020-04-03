package c24.thriftshop.stripe.demo;

import com.google.gson.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Service {
    public Customer getCustomer() {
        //Deserialization
        HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers/cus_H1o95j2ytbto3e")
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        String jsonString = response.getBody();
        Gson gson = new Gson();
        Customer customer = gson.fromJson(jsonString, Customer.class);
        return customer;
    }

    public Customer[] getAllCustomer() {
        //Deserialization
        HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers")
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        Gson gson = new Gson();
        String jsonString = response.getBody();
        JsonObject o = new JsonParser().parse(jsonString).getAsJsonObject();
        JsonArray data = (JsonArray) o.get("data");
        Customer[] customers = gson.fromJson(data, Customer[].class);
        return customers;
    }

}
