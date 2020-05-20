package c24.thriftshop.webjavalin.entity;

import java.util.Date;
import java.util.UUID;

public class DeviceEntity {

    private UUID id;
    private String deviceName;
    private int deviceId;
    private String deviceDescription;
    private boolean available;
    private String userId;
    private Date rentDate;
    private Date returnDate;

    public DeviceEntity(final UUID id, final String deviceName, final int deviceId, final String deviceDescription, final boolean available, final String userId, final Date rentDate, final Date returnDate) {
        this.id = id;
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.deviceDescription = deviceDescription;
        this.available = available;
        this.userId = userId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

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
