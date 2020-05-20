package c24.thriftshop.webjavalin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnRequest {
    private final String deviceName;
    private final int deviceId;

    public ReturnRequest(@JsonProperty(value = "deviceName", required = true) final String deviceName,
                         @JsonProperty(value = "deviceId") final int deviceId) {
        this.deviceName = deviceName;
        this.deviceId = deviceId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

}
