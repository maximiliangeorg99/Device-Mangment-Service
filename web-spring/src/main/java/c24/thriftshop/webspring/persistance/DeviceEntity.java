package c24.thriftshop.webspring.persistance;

import java.util.UUID;

public class DeviceEntity {
    private final UUID id;
    private final String name;
    private final boolean available;
    private final String email;
    private final String rentDate;
    private final String returnDate;

    public DeviceEntity(final UUID id, final String name, final boolean available, final String email, final String rentDate, final String returnDate) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public String getRentDate() {
        return rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}
