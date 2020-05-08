package c24.thriftshop.webspring.domain.device;

import java.util.ArrayList;

public class Devices {
    ArrayList<Device> list;

    public Devices(final ArrayList<Device> list) {
        this.list = list;
    }

    public ArrayList<Device> getList() {
        return list;
    }
}
