package c24.thriftshop.webspring.presentation.device;

import c24.thriftshop.webspring.domain.device.add.AddService;
import c24.thriftshop.webspring.domain.device.add.Addrequest;
import c24.thriftshop.webspring.domain.device.rent.RentRequest;
import c24.thriftshop.webspring.domain.device.rent.RentResponse;
import c24.thriftshop.webspring.domain.device.rent.RentService;
import c24.thriftshop.webspring.domain.device.returnn.ReturnRequest;
import c24.thriftshop.webspring.domain.device.returnn.ReturnResponse;
import c24.thriftshop.webspring.domain.device.returnn.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/device")
@RestController
public class DeviceController {

    private final RentService rentService;
    private final ReturnService returnService;
    private final AddService addService;

    @Autowired
    public DeviceController(final RentService rentService, final ReturnService returnService, final AddService addService) {
        this.rentService = rentService;
        this.returnService = returnService;
        this.addService = addService;
    }

    //should only be used by authenticated Admins
    @RequestMapping("/add")
    @PostMapping
    public void addRequest(@RequestBody final Addrequest request) {
        addService.execute(request);
    }

    @RequestMapping("/rent")
    @PostMapping
    public RentResponse rentDevice(@RequestBody final RentRequest request) {
        return rentService.execute(request);
    }

    @RequestMapping("/return")
    @PostMapping
    public ReturnResponse returnDevice(@RequestBody final ReturnRequest request) {
        return returnService.execute(request);
    }
}
