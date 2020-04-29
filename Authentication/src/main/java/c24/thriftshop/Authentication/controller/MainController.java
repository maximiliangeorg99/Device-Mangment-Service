package c24.thriftshop.Authentication.controller;

import c24.thriftshop.Authentication.model.LoginRequest;
import c24.thriftshop.Authentication.model.LoginResponse;
import c24.thriftshop.Authentication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final LoginService loginService;

    @Autowired
    public MainController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody final LoginRequest request) {
        return loginService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/authenticate")
    public boolean authenticate(@RequestHeader("Authorization") final String authorizationHeader) {
        final String token = authorizationHeader.replaceFirst("Bearer ", "");
        return loginService.authenticate(token);
    }
}