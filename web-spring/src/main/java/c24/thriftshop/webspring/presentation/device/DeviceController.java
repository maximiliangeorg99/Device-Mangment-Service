package c24.thriftshop.webspring.presentation.device;

import c24.thriftshop.webspring.domain.device.add.AddService;
import c24.thriftshop.webspring.domain.device.add.Addrequest;
import c24.thriftshop.webspring.domain.device.rent.RentRequest;
import c24.thriftshop.webspring.domain.device.rent.RentResponse;
import c24.thriftshop.webspring.domain.device.rent.RentService;
import c24.thriftshop.webspring.domain.device.returnn.ReturnRequest;
import c24.thriftshop.webspring.domain.device.returnn.ReturnResponse;
import c24.thriftshop.webspring.domain.device.returnn.ReturnService;
import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationRequest;
import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationResponse;
import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RequestMapping("/devices")
@RestController
public class DeviceController {

    private final RentService rentService;
    private final ReturnService returnService;
    private final AddService addService;
    private final AuthenticationService authenticationService;

    @Autowired
    public DeviceController(final RentService rentService, final ReturnService returnService, final AddService addService, final AuthenticationService authenticationService) {
        this.rentService = rentService;
        this.returnService = returnService;
        this.addService = addService;
        this.authenticationService = authenticationService;
    }

    //should only be used by authenticated Admins
    @RequestMapping("/add")
    @PostMapping
    public void addRequest(@RequestBody final Addrequest request) {
        addService.execute(request);
    }

    @RequestMapping("/rent")
    @PostMapping
    public RentResponse rentDevice(@RequestBody final RentRequest request, @RequestHeader("Authorization") final String authorizationHeader) {
        final String token = authorizationHeader.replaceFirst("Bearer ", "");
        final AuthenticationResponse response = authenticationService.execute(new AuthenticationRequest(token));
        if (response.isSuccessful()) {
            return rentService.execute(request, response.getId());
        } else {
            throw new ResponseStatusException(UNAUTHORIZED, "Not authorized to use this Service");
        }
    }

    @RequestMapping("/return")
    @PostMapping
    public ReturnResponse returnDevice(@RequestBody final ReturnRequest request, @RequestHeader("Authorization") final String authorizationHeader) {
        final String token = authorizationHeader.replaceFirst("Bearer ", "");
        final AuthenticationResponse response = authenticationService.execute(new AuthenticationRequest(token));
        if (response.isSuccessful()) {
            return returnService.execute(request, response.getId());
        } else {
            throw new ResponseStatusException(UNAUTHORIZED, "Not authorized to use this Service");
        }
    }
}
