package c24.thriftshop.webspring.presentation;

import c24.thriftshop.webspring.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    @PostMapping
    public String registerUser(@RequestBody final UserModel user) {
        return userService.registerUser(user.getEmail(), user.getPassword()).message();
    }

    @RequestMapping("/login")
    @PostMapping
    public String loginUser(@RequestBody final UserModel user) {
        return userService.loginUser(user.getEmail(), user.getPassword()).message();
    }

}