package c24.thriftshop.stripe.demo.domain.user;

import c24.thriftshop.stripe.demo.domain.Email;
import c24.thriftshop.stripe.demo.persistence.user.JsonUser;
import c24.thriftshop.stripe.demo.persistence.user.JsonUserRepository;

public class UserService {
    private final JsonUserRepository jsonUserRepository;

    public UserService(final JsonUserRepository jsonUserRepository) {
        this.jsonUserRepository = jsonUserRepository;
    }

    public RegistrationState registerUser(final String email, final String password) {
        if (emailExists(new Email(email))) {
            return RegistrationState.EmailAlreadyExists;
        } else if (!isValidPassword(password)) {
            return RegistrationState.WeakPassword;
        } else if (!isValidEmailAddressString(email)) {
            return RegistrationState.NotValidEmail;
        } else {
            final User user = new User(email, password);
            jsonUserRepository.save(new JsonUser(user));
            return RegistrationState.Successful;
        }
    }

    public LoginState loginUser(final String email, final String password) {
        if (!emailExists(new Email(email))) {
            return LoginState.EmailDoesNotExist;
        } else if (!checkPassword(email, password)) {
            return LoginState.WrongPassword;
        } else {
            return LoginState.Successful;
        }
    }

    private boolean checkPassword(final String email, final String password) {
        final User user = new User(jsonUserRepository.findByEmail(email).get());
        final String salt = jsonUserRepository.findByEmail(email).get().getSalt();
        final Password a = user.getPassword();
        final Password b = new Password(password, salt);
        return a.equals(b);
    }

    private boolean emailExists(final Email email) {
        return jsonUserRepository.findByEmail(email.getEmailAsString()).isPresent();
    }

    private boolean isValidPassword(final String password) {
        final boolean minLength = password.length() >= 8;
        final boolean hasUppercase = !password.equals(password.toLowerCase());
        final boolean hasLowercase = !password.equals(password.toUpperCase());
        final boolean hasSpecial = !password.matches("[A-Za-z0-9 ]*");//Checks at least one char is not alpha numeric
        final boolean hasNumbers = !password.matches("[A-Za-z]*");
        final boolean hasLetters = !password.matches("[0-9]*");
        return minLength && hasUppercase && hasLowercase && hasSpecial && hasNumbers && hasLetters;
    }

    private boolean isValidEmailAddressString(final String email) {
        final String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        final java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        final java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
