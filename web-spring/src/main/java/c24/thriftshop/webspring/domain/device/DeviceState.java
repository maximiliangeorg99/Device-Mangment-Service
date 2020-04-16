package c24.thriftshop.webspring.domain.device;

public enum DeviceState {
    DontOwnDevice, NotAvailable, Available;

    public String message() {
        switch (this) {
            case Available:
                return "was successful.";
            case NotAvailable:
                return "failed because device is not available.";
            case DontOwnDevice:
                return "failed you didnt rent the device.";
            default:
                return "Invalid State";
        }
    }
}
