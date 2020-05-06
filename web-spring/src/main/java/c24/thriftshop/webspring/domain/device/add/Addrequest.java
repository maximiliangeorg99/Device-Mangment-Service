package c24.thriftshop.webspring.domain.device.add;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Addrequest {
    String name;

    public Addrequest(@JsonProperty("name") final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
