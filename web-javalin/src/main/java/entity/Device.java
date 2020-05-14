package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
    private final boolean available;
    private final String description;
    private final int deviceId;

    public Device(@JsonProperty("available") final boolean available,
                  @JsonProperty("description") final String description,
                  @JsonProperty("deviceId") final int deviceId) {
        this.available = available;
        this.description = description;
        this.deviceId = deviceId;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getDescription() {
        return description;
    }

    public int getDeviceId() {
        return deviceId;
    }
}
