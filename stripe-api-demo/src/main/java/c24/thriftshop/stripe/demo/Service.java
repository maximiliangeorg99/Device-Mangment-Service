package c24.thriftshop.stripe.demo;

import com.google.gson.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Service {
    public Customer getCustomer(String id) throws ApiException {
        //Deserialization
        HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers/" + id)
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        String jsonString = response.getBody();
        Gson gson = new Gson();
        //Error
        if (!response.isSuccess()) {
            ApiException e = gson.fromJson(jsonString, ApiException.class);
            throw e;
        }
        Customer customer = gson.fromJson(jsonString, Customer.class);
        return customer;
    }

    public Customers getAllCustomer() throws ApiException {
        //Deserialization
        HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers")
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        Gson gson = new Gson();
        String jsonString = response.getBody();
        //Error
        if (!response.isSuccess()) {
            ApiException e = gson.fromJson(jsonString, ApiException.class);
            throw e;
        }
        Customers customers = gson.fromJson(jsonString, Customers.class);
        return customers;
    }

    public boolean deleteCustomer(String id) throws ApiException {

        HttpResponse<String> response = Unirest.delete("https://api.stripe.com/v1/customers/" + id)
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        String jsonString = response.getBody();
        Gson gson = new Gson();
        //Error
        if (!response.isSuccess()) {
            ApiException e = gson.fromJson(jsonString, ApiException.class);
            throw e;
        }
        DeletedMessage m = gson.fromJson(jsonString, DeletedMessage.class);
        return m.deleted;
    }

    public Customer createCustomer(Customer Input) throws ApiException {
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
        //Error
        if (!response.isSuccess()) {
            ApiException e = gson.fromJson(jsonString, ApiException.class);
            throw e;
        }
        Customer customer = gson.fromJson(jsonString, Customer.class);
        return customer;
    }

    public Customer updateCustomer(Customer Input, String oldId) throws ApiException {
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
        //Error
        if (!response.isSuccess()) {
            ApiException e = gson.fromJson(jsonString, ApiException.class);
            throw e;
        }
        Customer customer = gson.fromJson(jsonString, Customer.class);
        return customer;
    }
}
