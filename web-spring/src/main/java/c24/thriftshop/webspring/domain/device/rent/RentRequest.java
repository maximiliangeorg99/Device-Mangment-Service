package c24.thriftshop.webspring.domain.device.rent;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentRequest {
    private final String deviceName;
    private final int durationInHours;

    public RentRequest(@JsonProperty("deviceName") final String deviceName,
                       @JsonProperty("duration") final int durationInHours
    ) {
        this.deviceName = deviceName;
        this.durationInHours = durationInHours;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

}
