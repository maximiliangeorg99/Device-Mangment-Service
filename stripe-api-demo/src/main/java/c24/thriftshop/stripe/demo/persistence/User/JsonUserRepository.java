package c24.thriftshop.stripe.demo.persistence.User;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class JsonUserRepository implements UserRepository {
    private final File DB = new File("C:\\dev\\training\\Device-Managment-Service\\User-registration\\src\\main\\java\\c24\\therealthriftshop\\registration\\persistence\\JsonFiles", "users.json");

    Gson gson = new Gson();


    public JsonUserRepository() {

    }

    @Override
    public Optional<JsonUser> findById(final String s) {
        return Optional.empty();
    }

    @Override
    public Collection<JsonUser> findAll() {
        return null;
    }

    @Override
    public JsonUser save(final JsonUser entity) {
        final String JsonString = gson.toJson(entity);
        try (final FileWriter file = new FileWriter(DB)) {
            file.write(JsonString);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(final JsonUser entity) {

    }
}
