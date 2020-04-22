package c24.thriftshop.webspring.domain.device.returnn;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReturnResponse {
    ReturnMessage message;

    public ReturnResponse(@JsonProperty("message") final ReturnMessage message) {
        this.message = message;
    }

    public ReturnMessage getMessage() {
        return message;
    }
}
