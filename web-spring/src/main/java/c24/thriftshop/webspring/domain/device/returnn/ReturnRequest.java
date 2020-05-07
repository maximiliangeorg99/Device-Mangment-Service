package c24.thriftshop.webspring.domain.device.returnn;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnRequest {
    private final String deviceName;

    public ReturnRequest(@JsonProperty("deviceName") final String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

}
