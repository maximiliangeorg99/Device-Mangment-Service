package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentRequest {
    private final String deviceName;
    private final int durationInHours;
    private final int deviceId;

    public RentRequest(@JsonProperty(value = "deviceName", required = true) final String deviceName,
                       @JsonProperty(value = "duration", required = true) final int durationInHours,
                       @JsonProperty("deviceId") final int deviceId
    ) {
        this.deviceName = deviceName;
        this.durationInHours = durationInHours;
        this.deviceId = deviceId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

}
