package c24.thriftshop.webspring.domain.user.register;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterResponse {
    private final RegisterMessage message;

    public RegisterResponse(@JsonProperty("message") final RegisterMessage message) {
        this.message = message;
    }

    public RegisterMessage getMessage() {
        return message;
    }
}
