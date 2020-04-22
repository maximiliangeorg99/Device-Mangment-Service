package c24.thriftshop.webspring.domain.user.login;

import c24.thriftshop.webspring.domain.Password;
import c24.thriftshop.webspring.persistence.user.UserEntity;
import c24.thriftshop.webspring.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {
    private final UserRepository userRepository;

    @Autowired
    public LoginService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse execute(final LoginRequest loginRequest) {
        return userRepository.findByEmail(loginRequest.getEmail()).map(userEntity -> checkPassword(userEntity, loginRequest.getPassword())).orElseGet(() -> new LoginResponse(LoginMessage.EMAIL_NOT_REGISTERED, null));
    }

    private LoginResponse checkPassword(final UserEntity userEntity, final String password) {
        final String salt = userEntity.getSalt();
        final Password a = new Password(userEntity.getPassword());
        Password b = new Password(password, salt);
        b = new Password(b.getHash(), salt);
        if (a.equals(b)) {
            final UUID token = UUID.randomUUID();
            userEntity.setToken(token);
            return new LoginResponse(LoginMessage.SUCCESSFUL, token.toString());
        } else {
            return new LoginResponse(LoginMessage.WRONG_PASSWORD, null);
        }
    }
}
