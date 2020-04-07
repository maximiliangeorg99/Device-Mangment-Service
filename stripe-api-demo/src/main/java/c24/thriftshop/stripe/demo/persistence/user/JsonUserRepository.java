package c24.thriftshop.stripe.demo.persistence.user;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

public class JsonUserRepository implements UserRepository {
    private final File DB = new File("C:\\dev\\training\\Device-Managment-Service\\stripe-api-demo\\src\\main\\java\\c24\\thriftshop\\stripe\\demo\\persistence\\User\\JsonFiles", "users.json");

    Gson gson = new Gson();

    public JsonUserRepository() {
        final JsonUsers jsonUsers = new JsonUsers();
        try {
            final FileWriter writer = new FileWriter(DB);
            writer.write(gson.toJson(jsonUsers));
            writer.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<JsonUser> findById(final String s) {
        final Optional<JsonUser> optional;
        try (final Scanner scanner = new Scanner(DB)) {
            final JsonUsers jsonUsers = gson.fromJson(scanner.nextLine(), JsonUsers.class);
            for (final JsonUser jsonUser : jsonUsers.list) {
                if (jsonUser.getEmail().equalsIgnoreCase(s)) {
                    optional = Optional.of(jsonUser);
                } else {
                    optional = Optional.empty();
                }
                return optional;
            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        //?
        return null;
    }

    @Override
    public Collection<JsonUser> findAll() {
        try (final Scanner scanner = new Scanner(DB)) {
            final JsonUsers jsonUsers = gson.fromJson(scanner.nextLine(), JsonUsers.class);
            return jsonUsers.list;
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        //?
        return null;
    }

    @Override
    public JsonUser save(final JsonUser entity) {
        //simple slow implementation
        try {
            final Scanner s = new Scanner(DB);
            final String jsonString = s.nextLine();
            final JsonUsers jsonUsers = gson.fromJson(jsonString, JsonUsers.class);
            jsonUsers.list.add(entity);
            final String jsonStringWrite = gson.toJson(jsonUsers, JsonUsers.class);
            final FileWriter writer = new FileWriter(DB);
            writer.write(jsonStringWrite);
            writer.flush();
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void delete(final JsonUser entity) {
        try {
            final Scanner s = new Scanner(DB);
            final String jsonString = s.nextLine();
            final JsonUsers jsonUsers = gson.fromJson(jsonString, JsonUsers.class);
            jsonUsers.list.remove(entity);
            final String jsonStringWrite = gson.toJson(jsonUsers, JsonUsers.class);
            final FileWriter writer = new FileWriter(DB);
            writer.write(jsonStringWrite);
            writer.flush();
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
