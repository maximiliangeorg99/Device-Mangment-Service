package c24.demo.domain.entity;

public class User {
    private final String rolle;
    private final String name;
    private final String password;

    public User() {
        this.rolle = "";
        this.name = "";
        this.password = "";
    }

    public User(final String name, final String password, final String rolle) {
        this.name = name;
        this.password = password;
        this.rolle = rolle;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRolle() {
        return rolle;
    }
}
