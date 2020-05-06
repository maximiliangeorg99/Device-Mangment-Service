package c24.thriftshop.webspring.domain.device.rent;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentRequest {
    private final String deviceName;
    private final int durationInDays;
    private final String username;

    public RentRequest(@JsonProperty("deviceName") final String deviceName,
                       @JsonProperty("duration") final int durationInDays,
                       @JsonProperty("username") final String username) {
        this.deviceName = deviceName;
        this.durationInDays = durationInDays;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

}
