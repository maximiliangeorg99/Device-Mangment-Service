package c24.thriftshop.webspring.presentation.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel {

    String Email;
    String Password;

    public UserModel(@JsonProperty("email") final String email,
                     @JsonProperty("password") final String password) {
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
}
