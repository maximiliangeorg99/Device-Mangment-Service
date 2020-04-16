package c24.thriftshop.webspring.persistence.device;

import c24.thriftshop.webspring.domain.device.Device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Device")
public class DeviceEntity {
    @Id
    @Column(name = "id")
    private final UUID id;
    @Column(name = "name")
    private final String name;
    @Column(name = "available")
    private final boolean available;
    @Column(name = "email")
    private final String email;
    @Column(name = "rentDate")
    private final String rentDate;
    @Column(name = "returnDate")
    private final String returnDate;

    public DeviceEntity(final UUID id, final String name, final boolean available, final String email, final String rentDate, final String returnDate) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.email = email;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public DeviceEntity(final Device device) {
        this.id = device.getId();
        this.name = device.getName();
        this.available = device.isAvailable();
        this.email = device.getEmail().getEmailAsString();
        this.rentDate = device.getRentDate();
        this.returnDate = device.getReturnDate();
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
