package c24.thriftshop.webspring.presentation.user;

import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationRequest;
import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationResponse;
import c24.thriftshop.webspring.domain.user.authenticate.AuthenticationService;
import c24.thriftshop.webspring.domain.user.login.LoginRequest;
import c24.thriftshop.webspring.domain.user.login.LoginResponse;
import c24.thriftshop.webspring.domain.user.login.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequestMapping("/user")
@RestController
public class UserController {
    private final LoginService loginService;
    private final AuthenticationService authenticationService;

    public UserController(final LoginService loginService, final AuthenticationService authenticationService) {
        this.loginService = loginService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping("/authenticate")
    @PostMapping
    public AuthenticationResponse registerUser(@RequestBody final AuthenticationRequest request) {
        return authenticationService.execute(request);
    }

    @RequestMapping("/login")
    @PostMapping
    public LoginResponse loginUser(@RequestBody final LoginRequest request) {
        final LoginResponse response = loginService.execute(request);
        if (response.isSuccessful()) {
            return response;
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find user with this password");
        }
    }

}