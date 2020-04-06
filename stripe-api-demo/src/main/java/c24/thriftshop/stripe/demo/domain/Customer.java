package c24.thriftshop.stripe.demo.domain;

public class Customer {
    private String id;
    private String email;
    private String name;
    private String phone;
    private int balance;
    private String currency;

    public Customer(String id, String email, String name, String phone, int balance, String currency) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.balance = balance;
        this.currency = currency;
    }

    public String getId() {
        return id;
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

    public int getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }
}
