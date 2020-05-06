package c24.thriftshop.webspring.domain.device.returnn;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnRequest {
    private final String deviceName;
    private final String username;

    public ReturnRequest(@JsonProperty("deviceName") final String deviceName,
                         @JsonProperty("username") final String username) {
        this.deviceName = deviceName;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getDeviceName() {
        return deviceName;
    }

}
