package c24.thriftshop.webspring.persistence.device;

import c24.thriftshop.webspring.domain.device.Device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "DEVICE")
public class DeviceEntity {

    @Id
    @Column(name = "ID", length = 36)
    private UUID id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AVAILABLE")
    private boolean available;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "RENT_DATE")
    private Date rentDate;
    @Column(name = "RETURN_DATE")
    private Date returnDate;

    public DeviceEntity() {
    }

    public DeviceEntity(final UUID id, final String name, final boolean available, final String username, final Date rentDate, final Date returnDate) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.username = username;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public DeviceEntity(final Device device) {
        this.id = device.getId();
        this.name = device.getName();
        this.available = device.isAvailable();
        this.username = device.getUsername();
        this.rentDate = device.getRentDate();
        this.returnDate = device.getReturnDate();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(final boolean available) {
        this.available = available;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String email) {
        this.username = email;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(final Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final Date returnDate) {
        this.returnDate = returnDate;
    }
}
