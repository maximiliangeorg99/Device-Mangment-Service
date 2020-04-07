package c24.thriftshop.stripe.demo.domain.Customer;

import c24.thriftshop.stripe.demo.persistence.Customer.StripeCustomer;

//phone Klasse,Balance Klasse,Email Klasse,Currency Enum
public class Customer {
    private final String id;
    private final Email email;
    private final String name;
    private final Phone phone;
    private final Balance balance;
    private final Currency currency;

    public Customer(final String id, final String email, final String name, final String phone, final int balance, final String currency) {
        this.id = id;
        this.email = new Email(email);
        this.name = name;
        this.phone = new Phone(phone);
        this.balance = new Balance(balance);
        this.currency = Currency.valueOf(currency);
    }

    public Customer(final StripeCustomer stripeCustomer) {
        this.id = stripeCustomer.getId();
        this.email = new Email(stripeCustomer.getEmail());
        this.name = stripeCustomer.getName();
        this.phone = new Phone(stripeCustomer.getPhone());
        this.balance = new Balance(stripeCustomer.getBalance());
        this.currency = Currency.valueOf(stripeCustomer.getCurrency());
    }

    public String getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Balance getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

}
