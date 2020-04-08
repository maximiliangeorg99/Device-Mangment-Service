package c24.thriftshop.stripe.demo.persistence.user;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class JsonUserRepository implements UserRepository {
    private final File DB = new File("C:\\dev\\training\\Device-Managment-Service\\stripe-api-demo\\src\\main\\java\\c24\\thriftshop\\stripe\\demo\\persistence\\User\\JsonFiles", "users.json");
    Gson gson = new Gson();
    private FileWriter fileWriter;
    private Scanner scanner;

    public JsonUserRepository() {
        final JsonUsers jsonUsers = new JsonUsers();
        try {
            fileWriter = new FileWriter(DB);
            fileWriter.write(gson.toJson(jsonUsers));
            fileWriter.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private JsonUsers readDB() {
        try {
            scanner = new Scanner(DB);
            final String jsonString = scanner.nextLine();
            return gson.fromJson(jsonString, JsonUsers.class);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeDB(final JsonUsers jsonUsers) {
        final String jsonStringWrite = gson.toJson(jsonUsers, JsonUsers.class);
        try {
            fileWriter = new FileWriter(DB);
            fileWriter.write(jsonStringWrite);
            fileWriter.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public void softDelete(final JsonUser entity) {
        final JsonUsers jsonUsers = readDB();
        for (final JsonUser jsonUser : jsonUsers.list) {
            if (jsonUser.getId().equals(entity.getId())) {
                jsonUser.setActive(false);
                break;
            }
        }
        writeDB(jsonUsers);
    }

    public Optional<JsonUser> findByEmail(final String email) {
        final Optional<JsonUser> optional;
        final JsonUsers jsonUsers = readDB();
        for (final JsonUser jsonUser : jsonUsers.list) {
            if (jsonUser.getEmail().equalsIgnoreCase(email)) {
                optional = Optional.of(jsonUser);
                return optional;
            }
        }
        optional = Optional.empty();
        return optional;
    }

    @Override
    public Optional<JsonUser> findById(final UUID id) {
        final Optional<JsonUser> optional;
        final JsonUsers jsonUsers = readDB();
        for (final JsonUser jsonUser : jsonUsers.list) {
            if (jsonUser.getId().equals(id)) {
                optional = Optional.of(jsonUser);
                return optional;
            }
        }
        optional = Optional.empty();
        return optional;
    }

    @Override
    public Collection<JsonUser> findAll() {
        final JsonUsers jsonUsers = readDB();
        return jsonUsers.list;
    }

    @Override
    public JsonUser save(final JsonUser entity) {
        final JsonUsers jsonUsers = readDB();
        jsonUsers.list.add(entity);
        writeDB(jsonUsers);
        return entity;
    }

    @Override
    public void delete(final JsonUser entity) {
        final JsonUsers jsonUsers = readDB();
        jsonUsers.list.remove(entity);
        writeDB(jsonUsers);
    }
}
