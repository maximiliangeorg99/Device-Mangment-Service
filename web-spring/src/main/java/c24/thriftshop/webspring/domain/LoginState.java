package c24.thriftshop.webspring.domain;

public enum LoginState {
    Successful, EmailDoesNotExist, WrongPassword;

    public String message() {
        switch (this) {
            case Successful:
                return "was successful.";
            case WrongPassword:
                return "failed because you entered a wrong password.";
            case EmailDoesNotExist:
                return "failed because email does not exist.";
            default:
                return "Invalid State";
        }
    }
}
