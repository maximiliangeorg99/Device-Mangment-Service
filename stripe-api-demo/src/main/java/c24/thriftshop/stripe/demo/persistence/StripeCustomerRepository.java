package c24.thriftshop.stripe.demo.persistence;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Collection;
import java.util.Optional;

public class StripeCustomerRepository implements CustomerRepository {
    final Gson gson = new Gson();

    public StripeCustomer getCustomerById(final String id) {
        //Deserialization
        final HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers/" + id)
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        final String jsonString = response.getBody();
        return gson.fromJson(jsonString, StripeCustomer.class);
    }

    public StripeCustomers getAllCustomer() {
        //Deserialization
        final HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers")
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        final String jsonString = response.getBody();
        return gson.fromJson(jsonString, StripeCustomers.class);
    }

    public boolean deleteCustomerById(final String id) {

        final HttpResponse<String> response = Unirest.delete("https://api.stripe.com/v1/customers/" + id)
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        final String jsonString = response.getBody();
        return gson.fromJson(jsonString, DeletedMessage.class).deleted;
    }

    public StripeCustomer createCustomer(final StripeCustomer Input) {
        //Hard Coded Version
        final HttpResponse<String> response = Unirest.post("https://api.stripe.com/v1/customers?description="
                + "id=" + Input.getId() + "&"
                + "email=" + Input.getEmail() + "&"
                + "name=" + Input.getName() + "&"
                + "phone=" + Input.getPhone() + "&"
                + "balance=" + Input.getBalance()
        )
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        final String jsonString = response.getBody();
        return gson.fromJson(jsonString, StripeCustomer.class);
    }

    public StripeCustomer updateCustomerById(final StripeCustomer Input, final String oldId) {
        final HttpResponse<String> response = Unirest.post("https://api.stripe.com/v1/customers/" + oldId + "?"
                + "email=" + Input.getEmail() + "&"
                + "name=" + Input.getName() + "&"
                + "phone=" + Input.getPhone() + "&"
                + "balance=" + Input.getBalance()
        )
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        final String jsonString = response.getBody();
        return gson.fromJson(jsonString, StripeCustomer.class);
    }

    @Override
    public Optional<StripeCustomer> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public Collection<StripeCustomer> findAll() {
        return null;
    }

    @Override
    public StripeCustomer save(final StripeCustomer entity) {
        return null;
    }

    @Override
    public void delete(final StripeCustomer entity) {

    }
}