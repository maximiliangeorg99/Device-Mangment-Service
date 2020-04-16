package c24.thriftshop.webspring.presentation.device;

import c24.thriftshop.webspring.domain.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/device")
@RestController
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(final DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @RequestMapping("/rent")
    @PostMapping
    public String rentDevice(@RequestBody final DeviceModel device) {
        return deviceService.rentDevice(device.getName(), device.getEmail(), device.getDuration()).message();
    }

    @RequestMapping("/return")
    @PostMapping
    public String returnDevice(@RequestBody final DeviceModel device) {
        return deviceService.returnDevice(device.getName(), device.getEmail()).message();
    }
}
