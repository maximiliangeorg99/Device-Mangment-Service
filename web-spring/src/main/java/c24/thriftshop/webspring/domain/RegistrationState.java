package c24.thriftshop.webspring.domain;

public enum RegistrationState {
    Successful, WeakPassword, NotValidEmail, EmailAlreadyExists;

    public String message() {
        switch (this) {
            case Successful:
                return "was successful";
            case WeakPassword:
                return "failed because you entered a weak password.";
            case EmailAlreadyExists:
                return "failed because email does already exists.";
            case NotValidEmail:
                return "failed because you didn't enter a valid email.";
            default:
                return "Invalid State";
        }
    }
}
