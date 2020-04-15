package c24.thriftshop.webspring.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceModel {
    private final String name;
    private final int duration;

    public DeviceModel(@JsonProperty("name") final String name,
                       @JsonProperty("duration") final int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}
