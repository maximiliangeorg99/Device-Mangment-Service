package c24.thriftshop.webspring.domain.device;

import c24.thriftshop.webspring.persistence.device.DeviceEntity;

import java.util.ArrayList;

public class Devices {
    ArrayList<Device> list;

    public Devices(final ArrayList<DeviceEntity> list) {
        this.list = new ArrayList<>();
        for (final DeviceEntity e : list) {
            this.list.add(new Device(e.isAvailable(), e.getDeviceDescription(), e.getDeviceId()));
        }
    }

    public ArrayList<Device> getList() {
        return list;
    }
}
