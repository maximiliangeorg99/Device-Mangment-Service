package c24.thriftshop.stripe.demo.domain;

import c24.thriftshop.stripe.demo.presentation.Customer;
import c24.thriftshop.stripe.demo.presentation.Customers;
import com.google.gson.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class DataAccessService implements CustomerDao {
    public Customer getCustomerById(String id) {
        //Deserialization
        HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers/" + id)
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        String jsonString = response.getBody();
        Gson gson = new Gson();
        Customer customer = gson.fromJson(jsonString, Customer.class);
        return customer;
    }

    public Customers getAllCustomer() {
        //Deserialization
        HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers")
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        Gson gson = new Gson();
        String jsonString = response.getBody();
        Customers customers = gson.fromJson(jsonString, Customers.class);
        return customers;
    }

    public boolean deleteCustomerById(String id) {

        HttpResponse<String> response = Unirest.delete("https://api.stripe.com/v1/customers/" + id)
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        String jsonString = response.getBody();
        Gson gson = new Gson();
        DeletedMessage m = gson.fromJson(jsonString, DeletedMessage.class);
        return m.deleted;
    }

    public Customer createCustomer(Customer Input) {
        //Hard Coded Version
        HttpResponse<String> response = Unirest.post("https://api.stripe.com/v1/customers?description="
                + "id=" + Input.getId() + "&"
                + "email=" + Input.getEmail() + "&"
                + "name=" + Input.getName() + "&"
                + "phone=" + Input.getPhone() + "&"
                + "balance=" + Input.getBalance()
        )
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        String jsonString = response.getBody();
        Gson gson = new Gson();
        Customer customer = gson.fromJson(jsonString, Customer.class);
        return customer;
    }

    public Customer updateCustomerById(Customer Input, String oldId) {
        HttpResponse<String> response = Unirest.post("https://api.stripe.com/v1/customers/" + oldId + "?"
                + "email=" + Input.getEmail() + "&"
                + "name=" + Input.getName() + "&"
                + "phone=" + Input.getPhone() + "&"
                + "balance=" + Input.getBalance()
        )
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        String jsonString = response.getBody();
        Gson gson = new Gson();
        Customer customer = gson.fromJson(jsonString, Customer.class);
        return customer;
    }
}
