package c24.thriftshop.stripe.demo.domain.customer;

public class Phone {
    String phone;

    public Phone(final String phone) {
        this.phone = phone;
    }

    public String getPhoneAsString() {
        return phone;
    }
}
