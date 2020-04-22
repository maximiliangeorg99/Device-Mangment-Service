package c24.thriftshop.webspring.domain.device;

import c24.thriftshop.webspring.domain.Email;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Device {
    private final UUID id;
    private final String name;
    private final boolean available;
    private final Email email;
    private final Date rentDate;
    private final Date returnDate;

    public Device(final String name, final String email, final int duration) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.available = false;
        this.email = new Email(email);
        final Date curr = new Date();
        this.rentDate = curr;
        final Calendar c = Calendar.getInstance();
        c.setTime(curr);
        c.add(Calendar.DATE, duration);
        final Date then = c.getTime();
        this.returnDate = then;
    }

    public Device(final UUID id, final String name, final boolean available, final String email, final Date rentDate, final Date returnDate) {
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

    public Date getRentDate() {
        return rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
