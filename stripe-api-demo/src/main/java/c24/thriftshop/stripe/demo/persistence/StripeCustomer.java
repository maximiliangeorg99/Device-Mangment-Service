package c24.thriftshop.stripe.demo.persistence;

public class StripeCustomer {
    private String id;
    private String description;
    private String email;
    private String name;
    private String phone;
    private String object;
    private int balance;
    private String currency;

    public StripeCustomer() {

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

    public StripeCustomer(String id, String description, String email, String name, String phone, String object, int balance, String currency) {
        this.id = id;
        this.description = description;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.object = object;
        this.balance = balance;
        this.currency = currency;
    }
}
