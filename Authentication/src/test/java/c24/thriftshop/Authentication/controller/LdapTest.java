package c24.thriftshop.Authentication.controller;

import c24.thriftshop.Authentication.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class LdapTest {

    @Autowired
    LoginService authenticationService;

    @Test
    public void loginTest() {
        authenticationService.login("bob", "bobspassword");
    }
}