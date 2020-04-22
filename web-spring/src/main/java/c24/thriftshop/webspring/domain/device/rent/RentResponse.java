package c24.thriftshop.webspring.domain.device.rent;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class RentResponse {
    private final RentMessage message;
    private final boolean successful;
    private final Date returnDate;

    public RentResponse(@JsonProperty("message") final RentMessage message,
                        @JsonProperty("successful") final boolean successful,
                        @JsonProperty("return Date") final Date returnDate) {
        this.message = message;
        this.successful = successful;
        this.returnDate = returnDate;
    }

    public RentMessage getMessage() {
        return message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
