package c24.thriftshop.stripe.demo.presentation;

import c24.thriftshop.stripe.demo.domain.user.LoginState;
import c24.thriftshop.stripe.demo.domain.user.RegistrationState;
import c24.thriftshop.stripe.demo.domain.user.UserService;
import c24.thriftshop.stripe.demo.persistence.user.InMemoryUserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {
    static InMemoryUserRepository inMemoryUserRepository;
    static UserService userService;

    @Before
    public void setUp() throws Exception {
        inMemoryUserRepository = new InMemoryUserRepository();
        userService = new UserService(inMemoryUserRepository);
    }

    @Test
    public void testRegistration() {
        final String email = "maximilian@chrono24.com";
        final String pw = "Hallo12345678!";
        assertThat(userService.registerUser(email, pw)).isEqualTo(RegistrationState.Successful);
    }

    @Test
    public void testRegistrationEmailAlreadyExists() {
        final String email = "maximilian.georg@chrono24.com";
        final String pw = "Password12345678!";
        userService.registerUser(email, pw);
        assertThat(userService.registerUser(email, pw)).isEqualTo(RegistrationState.EmailAlreadyExists);
    }

    @Test
    public void testRegistrationWeakPasswordToShort() {
        final String email = "maximilian.georg1@chrono24.com";
        final String pw = "hi!";
        assertThat(userService.registerUser(email, pw)).isEqualTo(RegistrationState.WeakPassword);
    }

    @Test
    public void testRegistrationWeakPasswordNoSpecialCase() {
        final String email = "maximilian.georg2@chrono24.com";
        final String pw = "Password12345678";
        assertThat(userService.registerUser(email, pw)).isEqualTo(RegistrationState.WeakPassword);
    }

    @Test
    public void testRegistrationWeakPasswordNoLetter() {
        final String email = "maximilian.georg3@chrono24.com";
        final String pw = "12345678!";
        assertThat(userService.registerUser(email, pw)).isEqualTo(RegistrationState.WeakPassword);
    }

    @Test
    public void testRegistrationWeakPasswordNoNumber() {
        final String email = "maximilian.georg4@chrono24.com";
        final String pw = "Password!";
        assertThat(userService.registerUser(email, pw)).isEqualTo(RegistrationState.WeakPassword);
    }

    @Test
    public void testRegistrationInValidEmailNoAt() {
        final String email = "maximilian.georg.chrono24.com";
        final String pw = "Hallo12345678!";
        assertThat(userService.registerUser(email, pw)).isEqualTo(RegistrationState.NotValidEmail);
    }

    @Test
    public void testRegistrationInValidEmailNothingBeforeAt() {
        final String email = "@chrono24.com";
        final String pw = "Hallo12345678!";
        assertThat(userService.registerUser(email, pw)).isEqualTo(RegistrationState.NotValidEmail);
    }

    @Test
    public void testRegistrationInValidEmailEmpty() {
        final String email = "";
        final String pw = "Hallo12345678!";
        assertThat(userService.registerUser(email, pw)).isEqualTo(RegistrationState.NotValidEmail);
    }

    @Test
    public void testLoginSuccessfulAfterRegistration() {
        final String email = "max.georg@web.de";
        final String pw = "Password1234!";
        userService.registerUser(email, pw);
        assertThat(userService.loginUser(email, pw)).isEqualTo(LoginState.Successful);
    }

    @Test
    public void testLoginNotRegenerated() {
        final String email = "max.georg@web.de";
        final String pw = "Password1234!";
        assertThat(userService.loginUser(email, pw)).isEqualTo(LoginState.EmailDoesNotExist);
    }

    @Test
    public void testLoginWrongPassword() {
        final String email = "max.georg1234@web.de";
        final String pw = "Password1234!";
        userService.registerUser(email, pw);
        assertThat(userService.loginUser(email, pw + "hallo")).isEqualTo(LoginState.WrongPassword);
    }

}