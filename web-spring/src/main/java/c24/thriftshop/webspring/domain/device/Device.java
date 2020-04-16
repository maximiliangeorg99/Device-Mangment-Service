package c24.thriftshop.webspring.domain.device;

import c24.thriftshop.webspring.domain.Email;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Device {
    private final UUID id;
    private final String name;
    private final boolean available;
    private final Email email;
    private final String rentDate;
    private final String returnDate;

    //TODO crappy
    public Device(final String name, final String email, final int Duration) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.available = false;
        this.email = new Email(email);
        final Date curr = new Date();
        this.rentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(curr);
        curr.setHours(curr.getHours() + Duration);
        this.returnDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(curr);
    }

    //flat copy
    public Device(final UUID id, final String name, final boolean available, final String email, final String rentDate, final String returnDate) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.email = new Email(email);
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

    public Email getEmail() {
        return email;
    }

    public String getRentDate() {
        return rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}
