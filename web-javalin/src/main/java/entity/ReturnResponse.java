package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnResponse {
    private final String message;
    private final boolean successful;

    public ReturnResponse(@JsonProperty("message") final String message, @JsonProperty("successful") final boolean successful) {
        this.message = message;
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}
