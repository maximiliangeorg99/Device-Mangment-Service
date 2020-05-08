package c24.thriftshop.webspring.domain.device.add;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Addrequest {
    String name;
    String description;

    public Addrequest(@JsonProperty(value = "name", required = true) final String name,
                      @JsonProperty("description") final String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
