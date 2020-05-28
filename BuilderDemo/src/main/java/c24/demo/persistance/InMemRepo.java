package c24.demo.persistance;

import c24.demo.domain.entity.User;

import java.util.ArrayList;

public class InMemRepo {
    ArrayList<User> db;

    public InMemRepo() {
        db = new ArrayList<>();
    }

    public void save(final User user) {
        if (!db.contains(user))
            db.add(user);
    }

    public User find(final User user) {
        if (db.contains(user))
            return db.get(db.lastIndexOf(user));
        else
            return null;
    }
}
