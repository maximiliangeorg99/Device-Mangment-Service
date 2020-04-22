package c24.thriftshop.webspring.presentation.device;

import c24.thriftshop.webspring.domain.device.rent.RentRequest;
import c24.thriftshop.webspring.domain.device.rent.RentResponse;
import c24.thriftshop.webspring.domain.device.rent.RentService;
import c24.thriftshop.webspring.domain.device.returnn.ReturnRequest;
import c24.thriftshop.webspring.domain.device.returnn.ReturnResponse;
import c24.thriftshop.webspring.domain.device.returnn.ReturnService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/device")
@RestController
public class DeviceController {

    private final RentService rentService;
    private final ReturnService returnService;

    public DeviceController(final RentService rentService, final ReturnService returnService) {
        this.rentService = rentService;
        this.returnService = returnService;
    }

    @RequestMapping("/rent")
    @PostMapping
    public RentResponse rentDevice(@RequestBody final RentRequest rentRequest) {
        return rentService.execute(rentRequest);
    }

    @RequestMapping("/return")
    @PostMapping
    public ReturnResponse returnDevice(@RequestBody final ReturnRequest returnRequest) {
        return returnService.execute(returnRequest);
    }
}
