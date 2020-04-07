package c24.thriftshop.stripe.demo.presentation;

import c24.thriftshop.stripe.demo.domain.Customer;
import c24.thriftshop.stripe.demo.domain.Email;
import c24.thriftshop.stripe.demo.domain.Phone;

import java.text.DecimalFormat;

public class CustomerModel {
    private static final DecimalFormat df2 = new DecimalFormat("#,##");
    private final String id;
    private final Email email;
    private final String name;
    private final Phone phone;
    private final double balance;
    private final String currency;

    public CustomerModel(final Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.balance = customer.getBalance().getBalanceAsInt();
        this.currency = customer.getCurrency().toString();
    }

    @Override
    public String toString() {
        return name + ":{" +
                "email=" + email.getEmailAsString() +
                ", phone=" + phone.getPhoneAsString() +
                ", balance=" + getBalance() + getCurrency() +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email.getEmailAsString();
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone.getPhoneAsString();
    }

    public String getBalance() {
        return df2.format(balance);
    }

    public String getCurrency() {
        switch (currency) {
            case "usd":
                return "$";
            case "eur":
                return "€";
            case "chf":
                return "Fr";
            case "gbp":
                return "£";
            case "rub":
                return "₽";
            case "cny":
                return "¥";
            default:
                throw new IllegalStateException("Unexpected value: " + currency);
        }
    }
}
