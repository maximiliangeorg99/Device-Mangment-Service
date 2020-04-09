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
        final UserEntities userEntities = new UserEntities();
        try {
            fileWriter = new FileWriter(DB);
            fileWriter.write(gson.toJson(userEntities));
            fileWriter.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private UserEntities readDB() {
        try {
            scanner = new Scanner(DB);
            final String jsonString = scanner.nextLine();
            return gson.fromJson(jsonString, UserEntities.class);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeDB(final UserEntities userEntities) {
        final String jsonStringWrite = gson.toJson(userEntities, UserEntities.class);
        try {
            fileWriter = new FileWriter(DB);
            fileWriter.write(jsonStringWrite);
            fileWriter.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void softDelete(final UserEntity entity) {
        final UserEntities userEntities = readDB();
        for (final UserEntity userEntity : userEntities.list) {
            if (userEntity.getId().equals(entity.getId())) {
                userEntity.setActive(false);
                break;
            }
        }
        writeDB(userEntities);
    }

    @Override
    public Optional<UserEntity> findByEmail(final String email) {
        final Optional<UserEntity> optional;
        final UserEntities userEntities = readDB();
        for (final UserEntity userEntity : userEntities.list) {
            if (userEntity.getEmail().equalsIgnoreCase(email) && userEntity.isActive()) {
                optional = Optional.of(userEntity);
                return optional;
            }
        }
        optional = Optional.empty();
        return optional;
    }

    @Override
    public Optional<UserEntity> findById(final UUID id) {
        final Optional<UserEntity> optional;
        final UserEntities userEntities = readDB();
        for (final UserEntity userEntity : userEntities.list) {
            if (userEntity.getId().equals(id) && userEntity.isActive()) {
                optional = Optional.of(userEntity);
                return optional;
            }
        }
        optional = Optional.empty();
        return optional;
    }

    @Override
    public Collection<UserEntity> findAll() {
        final UserEntities userEntities = readDB();
        return userEntities.list;
    }

    @Override
    public UserEntity save(final UserEntity entity) {
        final UserEntities userEntities = readDB();
        userEntities.list.add(entity);
        writeDB(userEntities);
        return entity;
    }

    @Override
    public void delete(final UserEntity entity) {
        final UserEntities userEntities = readDB();
        userEntities.list.remove(entity);
        writeDB(userEntities);
    }
}
