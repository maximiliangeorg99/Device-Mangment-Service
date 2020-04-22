package c24.thriftshop.webspring.domain.user.register;

import c24.thriftshop.webspring.domain.Password;
import c24.thriftshop.webspring.persistence.user.UserEntity;
import c24.thriftshop.webspring.persistence.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {
    private final UserRepository userRepository;

    @Autowired
    public RegisterService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegisterResponse execute(final RegisterRequest registerRequest) {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(registerRequest.getEmail());
        final Password password = new Password(registerRequest.getPassword());
        if (optionalUserEntity.isPresent()) {
            return new RegisterResponse(RegisterMessage.EMAIL_EXISTS);
        } else if (!password.isValidPassword(registerRequest.getPassword())) {
            return new RegisterResponse(RegisterMessage.WEAK_PASSWORD);
        } else {
            return new RegisterResponse(RegisterMessage.SUCCESSFUL);
        }
    }
}
