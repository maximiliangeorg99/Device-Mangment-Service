package c24.thriftshop.webspring.domain.device.returnn;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnRequest {
    private final String deviceName;
    private final String token;
    private final String email;

    public ReturnRequest(@JsonProperty("deviceName") final String deviceName,
                         @JsonProperty("token") final String token,
                         @JsonProperty("email") final String email) {
        this.deviceName = deviceName;
        this.token = token;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getToken() {
        return token;
    }
}
