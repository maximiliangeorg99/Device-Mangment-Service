package c24.thriftshop.webspring.domain.device.rent;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentRequest {
    private final String deviceName;
    private final int durationInDays;
    private final String token;
    private final String email;

    public RentRequest(@JsonProperty("deviceName") final String deviceName,
                       @JsonProperty("duration") final int durationInDays,
                       @JsonProperty("token") final String token,
                       @JsonProperty("email") final String email) {
        this.deviceName = deviceName;
        this.durationInDays = durationInDays;
        this.token = token;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public String getToken() {
        return token;
    }
}
