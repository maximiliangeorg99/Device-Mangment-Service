package c24.thriftshop.webjavalin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentResponse {
    private final String message;
    private final boolean successful;
    private final Devices devices;

    public RentResponse(@JsonProperty("message") final String message,
                        @JsonProperty("successful") final boolean successful) {
        this.message = message;
        this.successful = successful;
        devices = null;
    }

    public RentResponse(@JsonProperty("message") final String message,
                        @JsonProperty("successful") final boolean successful,
                        @JsonProperty("devices") final Devices devices) {
        this.message = message;
        this.successful = successful;
        this.devices = devices;
    }

    public Devices getDevices() {
        return devices;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
