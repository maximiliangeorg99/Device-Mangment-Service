package c24.thriftshop.webspring.presentation.user;

import c24.thriftshop.webspring.domain.user.login.LoginRequest;
import c24.thriftshop.webspring.domain.user.login.LoginResponse;
import c24.thriftshop.webspring.domain.user.login.LoginService;
import c24.thriftshop.webspring.domain.user.register.RegisterRequest;
import c24.thriftshop.webspring.domain.user.register.RegisterResponse;
import c24.thriftshop.webspring.domain.user.register.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    private final LoginService loginService;
    private final RegisterService registerService;

    public UserController(final LoginService loginService, final RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    @RequestMapping("/register")
    @PostMapping
    public RegisterResponse registerUser(@RequestBody final RegisterRequest registerRequest) {
        return registerService.execute(registerRequest);
    }

    @RequestMapping("/login")
    @PostMapping
    public LoginResponse loginUser(@RequestBody final LoginRequest loginRequest) {
        return loginService.execute(loginRequest);
    }
}