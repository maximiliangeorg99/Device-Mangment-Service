package c24.thriftshop.stripe.demo.domain;

public class Email {
    String email;

    public Email(final String email) {
        this.email = email;
    }

    public String getEmailAsString() {
        return email;
    }

    //Very simplified!
    public boolean isValidEmailAddress(final String email) {
        final String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        final java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        final java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
