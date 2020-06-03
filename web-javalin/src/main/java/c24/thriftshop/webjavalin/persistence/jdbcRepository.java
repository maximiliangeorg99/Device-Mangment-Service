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
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://c24:Chrono24!@localhost:3306/");
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<DeviceEntity> findByDeviceName(final String name) {
        Optional<DeviceEntity> optional = Optional.empty();
        try {
            final String sql = "SELECT * FROM DEVICE device WHERE DEVICE_NAME = ?";
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            final ResultSet rs = statement.executeQuery(sql);
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
            final ResultSet rs = statement.executeQuery(sql);
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
    public ArrayList<DeviceEntity> findAllByDeviceName(final String name) {
        return null;
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
            final ResultSet rs = statement.executeQuery(sql);
            final DeviceEntity deviceEntity = new DeviceEntity(UUID.fromString(rs.getString("ID")), rs.getString("DEVICE_NAME"), rs.getInt("DEVICE_ID"), rs.getString("DEVICE_DESCRIPTION"), rs.getBoolean("AVAILABLE"), rs.getString("USER_ID"), rs.getDate("RENT_DATE"), rs.getDate("RETURN_DATE"));
            optional = Optional.of(deviceEntity);
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return optional;
    }

    @Override
    public DeviceEntity save(final DeviceEntity entity) {
        final String sql = "INSERT INTO DEVICE (ID, AVAILABLE, DEVICE_DESCRIPTION, DEVICE_ID, DEVICE_NAME, RENT_DATE, RETURN_DATE, USER_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getId().toString());
            statement.setBoolean(2, entity.isAvailable());
            statement.setString(3, entity.getDeviceDescription());
            statement.setInt(4, entity.getDeviceId());
            statement.setString(5, entity.getDeviceName());
            statement.setDate(6, javaUtilDateToSqlDate(entity.getRentDate()));
            statement.setDate(7, javaUtilDateToSqlDate(entity.getReturnDate()));
            statement.setString(8, entity.getUserId());
            statement.executeUpdate();
        } catch (final SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    private java.sql.Date javaUtilDateToSqlDate(final java.util.Date date) {
        final java.util.Calendar cal = Calendar.getInstance();
        final java.util.Date utilDate = new java.util.Date(); // your util date
        cal.setTime(utilDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(cal.getTime().getTime());
    }
}
