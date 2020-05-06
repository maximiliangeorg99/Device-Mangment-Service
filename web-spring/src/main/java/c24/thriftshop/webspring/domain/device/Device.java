package c24.thriftshop.webspring.domain.device;

import java.util.Date;
import java.util.UUID;

public class Device {
    private final UUID id;
    private final String name;
    private final boolean available;
    private final String username;
    private final Date rentDate;
    private final Date returnDate;

    public Device(final UUID id, final String name, final boolean available, final String username, final Date rentDate, final Date returnDate) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.username = username;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getUsername() {
        return username;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
