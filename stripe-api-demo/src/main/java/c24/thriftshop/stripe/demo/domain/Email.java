package c24.thriftshop.stripe.demo.domain;

public class Email {
    String email;

    public Email(final String email) {
        this.email = email;
    }

    public String getEmailAsString() {
        return email;
    }
}
