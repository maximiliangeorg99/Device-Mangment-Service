package c24.thriftshop.stripe.demo.persistence.Customer;

import c24.thriftshop.stripe.demo.domain.Customer.Customer;

public class StripeCustomer {
    private String id;
    private String description;
    private String email;
    private String name;
    private String phone;
    private String object;
    private int balance;
    private String currency;

    public StripeCustomer(final Customer customer) {
        final String id = customer.getId();
        final String email = customer.getEmail().getEmailAsString();
        final String name = customer.getName();
        final String phone = customer.getPhone().getPhoneAsString();
        final int balance = customer.getBalance().getBalanceAsInt();
        final String currency = customer.getCurrency().toString();
    }

    public StripeCustomer(final String id, final String description, final String email, final String name, final String phone, final String object, final int balance, final String currency) {
        this.id = id;
        this.description = description;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.object = object;
        this.balance = balance;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getObject() {
        return object;
    }

    public int getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }
}
