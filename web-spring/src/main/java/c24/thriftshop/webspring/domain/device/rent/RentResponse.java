package c24.thriftshop.webspring.domain.device.rent;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentResponse {
    private final String message;
    private final boolean successful;

    public RentResponse(@JsonProperty("message") final String message,
                        @JsonProperty("successful") final boolean successful
    ) {
        this.message = message;
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
