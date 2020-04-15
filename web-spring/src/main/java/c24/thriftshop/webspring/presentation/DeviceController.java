package c24.thriftshop.webspring.presentation;

import c24.thriftshop.webspring.domain.DeviceService;
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
        return null;
    }

    @RequestMapping("/return")
    @PostMapping
    public String returnDevice(@RequestBody final DeviceModel device) {
        return null;
    }
}
