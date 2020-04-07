package c24.thriftshop.stripe.demo.persistence.Customer;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.Collection;
import java.util.Optional;

public class StripeCustomerRepository implements CustomerRepository {
    final Gson gson = new Gson();

    @Override
    public Optional<StripeCustomer> findById(final String s) {
        final HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers/" + s)
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        final String jsonString = response.getBody();
        return Optional.ofNullable(gson.fromJson(jsonString, StripeCustomer.class));
    }

    @Override
    public Collection<StripeCustomer> findAll() {
        final HttpResponse<String> response = Unirest.get("https://api.stripe.com/v1/customers")
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        final String jsonString = response.getBody();
        return gson.fromJson(jsonString, StripeCustomers.class).getlist();
    }

    @Override
    public StripeCustomer save(final StripeCustomer entity) {
        final HttpResponse<String> response = Unirest.post("https://api.stripe.com/v1/customers?description="
                + "id=" + entity.getId() + "&"
                + "email=" + entity.getEmail() + "&"
                + "name=" + entity.getName() + "&"
                + "phone=" + entity.getPhone() + "&"
                + "balance=" + entity.getBalance()
        )
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
        final String jsonString = response.getBody();
        return gson.fromJson(jsonString, StripeCustomer.class);
    }

    @Override
    public void delete(final StripeCustomer entity) {
        final HttpResponse<String> response = Unirest.delete("https://api.stripe.com/v1/customers/" + entity.getId())
                .header("authorization", "Basic c2tfdGVzdF80ZUMzOUhxTHlqV0Rhcmp0VDF6ZHA3ZGM6Og==")
                .asString();
    }
}