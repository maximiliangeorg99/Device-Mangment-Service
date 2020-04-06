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
