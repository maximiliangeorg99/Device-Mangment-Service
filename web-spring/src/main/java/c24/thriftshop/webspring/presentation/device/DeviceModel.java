package c24.thriftshop.webspring.presentation.device;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceModel {
    private final String name;
    private final String email;
    private final int duration;

    public DeviceModel(@JsonProperty("name") final String name,
                       @JsonProperty("email") final String email,
                       @JsonProperty("duration") final int duration) {
        this.name = name;
        this.email = email;
        this.duration = duration;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}
