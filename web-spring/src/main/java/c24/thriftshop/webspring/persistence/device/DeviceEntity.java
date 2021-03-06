package c24.thriftshop.webspring.persistence.device;

import org.hibernate.annotations.Type;

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
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(name = "DEVICE_NAME")
    private String deviceName;
    //Because we don't have a lot of Devices with the same Name this is implemented with an increasing counter
    @Column(name = "DEVICE_ID")
    private int deviceId;
    @Column(name = "DEVICE_DESCRIPTION")
    private String deviceDescription;
    @Column(name = "AVAILABLE")
    private boolean available;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "RENT_DATE")
    private Date rentDate;
    @Column(name = "RETURN_DATE")
    private Date returnDate;

    public DeviceEntity() {
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(final int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(final String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(final String name) {
        this.deviceName = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(final boolean available) {
        this.available = available;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String email) {
        this.userId = email;
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
