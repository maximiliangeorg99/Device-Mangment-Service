package c24.thriftshop.webjavalin.persistence;

import c24.thriftshop.webjavalin.entity.DeviceEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

public class jdbcRepository implements DeviceRepository {
    private Connection connection = null;

    public jdbcRepository() {
        final String DB_URL = "jdbc:mysql://localhost:3306/thriftshop";
        final String USER = "c24";
        final String PASS = "Chrono24!";
        try {
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<DeviceEntity> findByDeviceName(final String name) {
        Optional<DeviceEntity> optional = Optional.empty();
        try {
            final String sql = "SELECT * FROM DEVICE WHERE DEVICE_NAME = ?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            final ResultSet rs = statement.executeQuery();
            rs.next();
            System.out.println(rs.getString("ID"));
            final DeviceEntity deviceEntity = new DeviceEntity(UUID.fromString(rs.getString("ID")), rs.getString("DEVICE_NAME"), rs.getInt("DEVICE_ID"), rs.getString("DEVICE_DESCRIPTION"), rs.getBoolean("AVAILABLE"), rs.getString("USER_ID"), rs.getDate("RENT_DATE"), rs.getDate("RETURN_DATE"));
            optional = Optional.of(deviceEntity);
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return optional;
    }

    @Override
    public Optional<DeviceEntity> findByDeviceNameAndDeviceId(final String name, final int id) {
        Optional<DeviceEntity> optional = Optional.empty();
        try {
            final String sql = "SELECT * FROM DEVICE device WHERE DEVICE_NAME =? AND DEVICE_ID=?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, String.valueOf(id));
            final ResultSet rs = statement.executeQuery();
            rs.first();
            final DeviceEntity deviceEntity = new DeviceEntity(UUID.fromString(rs.getString("ID")), rs.getString("DEVICE_NAME"), rs.getInt("DEVICE_ID"), rs.getString("DEVICE_DESCRIPTION"), rs.getBoolean("AVAILABLE"), rs.getString("USER_ID"), rs.getDate("RENT_DATE"), rs.getDate("RETURN_DATE"));
            optional = Optional.of(deviceEntity);
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return optional;
    }

    @Override
    public int countByDeviceName(final String name) {
        int c = 0;
        try {
            final String sql = "SELECT * FROM DEVICE WHERE DEVICE_NAME=?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                c++;
            }
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }

    @Override
    public ArrayList<DeviceEntity> findAllByDeviceName(final String name) {
        final ArrayList<DeviceEntity> deviceEntities = new ArrayList<>();
        try {
            final String sql = "SELECT * FROM DEVICE WHERE DEVICE_NAME=?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                final DeviceEntity deviceEntity = new DeviceEntity(UUID.fromString(rs.getString("ID")), rs.getString("DEVICE_NAME"), rs.getInt("DEVICE_ID"), rs.getString("DEVICE_DESCRIPTION"), rs.getBoolean("AVAILABLE"), rs.getString("USER_ID"), rs.getDate("RENT_DATE"), rs.getDate("RETURN_DATE"));
                deviceEntities.add(deviceEntity);
            }
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return deviceEntities;
    }

    @Override
    public long count() {
        int c = 0;
        try {
            final String sql = "SELECT * FROM DEVICE";
            final Statement statement = connection.createStatement();
            final ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                c++;
            }
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }

    @Override
    public void delete(final DeviceEntity entity) {
        try {
            final String sql = "DELETE FROM DEVICE WHERE ID=?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getId().toString());
            statement.executeUpdate();
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteById(final UUID uuid) {
        try {
            final String sql = "DELETE FROM DEVICE WHERE ID=?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, uuid.toString());
            statement.executeUpdate();
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean existsById(final UUID uuid) {
        return findById(uuid).isPresent();
    }

    @Override
    public Iterable<DeviceEntity> findAll() {
        final ArrayList<DeviceEntity> deviceEntities = new ArrayList<>();
        try {
            final String sql = "SELECT * FROM DEVICE";
            final Statement statement = connection.createStatement();
            final ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                final DeviceEntity deviceEntity = new DeviceEntity(UUID.fromString(rs.getString("ID")), rs.getString("DEVICE_NAME"), rs.getInt("DEVICE_ID"), rs.getString("DEVICE_DESCRIPTION"), rs.getBoolean("AVAILABLE"), rs.getString("USER_ID"), rs.getDate("RENT_DATE"), rs.getDate("RETURN_DATE"));
                deviceEntities.add(deviceEntity);
            }
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return deviceEntities;
    }

    @Override
    public Optional<DeviceEntity> findById(final UUID uuid) {
        Optional<DeviceEntity> optional = Optional.empty();
        try {
            final String sql = "SELECT * FROM DEVICE device WHERE ID = ?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, uuid.toString());
            final ResultSet rs = statement.executeQuery();
            rs.first();
            final DeviceEntity deviceEntity = new DeviceEntity(UUID.fromString(rs.getString("ID")), rs.getString("DEVICE_NAME"), rs.getInt("DEVICE_ID"), rs.getString("DEVICE_DESCRIPTION"), rs.getBoolean("AVAILABLE"), rs.getString("USER_ID"), rs.getDate("RENT_DATE"), rs.getDate("RETURN_DATE"));
            optional = Optional.of(deviceEntity);
        } catch (final SQLException throwables) {
        }
        return optional;
    }

    @Override
    public DeviceEntity save(final DeviceEntity entity) {
        final String sql;
        final PreparedStatement statement;
        try {
            if (!existsById(entity.getId())) {
                sql = "INSERT INTO DEVICE (ID, AVAILABLE, DEVICE_DESCRIPTION, DEVICE_ID, DEVICE_NAME, RENT_DATE, RETURN_DATE, USER_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, entity.getId().toString());
                statement.setBoolean(2, entity.isAvailable());
                statement.setString(3, entity.getDeviceDescription());
                statement.setInt(4, entity.getDeviceId());
                statement.setString(5, entity.getDeviceName());
                statement.setDate(6, javaUtilDateToSqlDate(entity.getRentDate()));
                statement.setDate(7, javaUtilDateToSqlDate(entity.getReturnDate()));
                statement.setString(8, entity.getUserId());
            } else {
                sql = "UPDATE DEVICE SET AVAILABLE=? , DEVICE_DESCRIPTION=? , DEVICE_ID=? , DEVICE_NAME=? , RENT_DATE=? , RETURN_DATE=? , USER_ID=? WHERE ID=?";
                statement = connection.prepareStatement(sql);
                statement.setBoolean(1, entity.isAvailable());
                statement.setString(2, entity.getDeviceDescription());
                statement.setInt(3, entity.getDeviceId());
                statement.setString(4, entity.getDeviceName());
                statement.setDate(5, javaUtilDateToSqlDate(entity.getRentDate()));
                statement.setDate(6, javaUtilDateToSqlDate(entity.getReturnDate()));
                statement.setString(7, entity.getUserId());
                statement.setString(8, entity.getId().toString());
            }
            statement.executeUpdate();
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    private java.sql.Date javaUtilDateToSqlDate(final java.util.Date date) {
        if (date == null) {
            return null;
        }
        java.sql.Date sDate = new java.sql.Date(date.getTime());
        return sDate;
    }
}
