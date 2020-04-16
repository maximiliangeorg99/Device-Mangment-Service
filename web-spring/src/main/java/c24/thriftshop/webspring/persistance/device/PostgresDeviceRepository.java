package c24.thriftshop.webspring.persistance.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository("devicePostgres")
public class PostgresDeviceRepository implements DeviceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresDeviceRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<DeviceEntity> findByName(final String deviceName) {
        final String sql = "SELECT * FROM device WHERE name = ?";
        DeviceEntity deviceEntity = null;
        try {
            deviceEntity = jdbcTemplate.queryForObject(sql, new Object[]{deviceName}, (resultSet, i) -> {
                return new DeviceEntity(resultSet.getObject("id", UUID.class),
                        resultSet.getString("name"),
                        resultSet.getBoolean("available"),
                        resultSet.getString("useremail"),
                        resultSet.getString("rent"),
                        resultSet.getString("return")
                );
            });
        } catch (final Exception ex) {

        }
        return Optional.ofNullable(deviceEntity);
    }

    @Override
    public void updateAvailable(final String entity, final boolean b) {
        final String sql = "UPDATE device SET available=? WHERE name=?";
        jdbcTemplate.update(sql, b, entity);
    }

    @Override
    public Optional<DeviceEntity> findById(final UUID uuid) {
        final String sql = "SELECT * FROM device WHERE id = ? AND available = 'true'";
        DeviceEntity deviceEntity = null;
        try {
            deviceEntity = jdbcTemplate.queryForObject(sql, new Object[]{uuid}, (resultSet, i) -> {
                return new DeviceEntity(resultSet.getObject("id", UUID.class),
                        resultSet.getString("name"),
                        resultSet.getBoolean("available"),
                        resultSet.getString("useremail"),
                        resultSet.getString("rent"),
                        resultSet.getString("return")
                );
            });
        } catch (final Exception ex) {

        }
        return Optional.ofNullable(deviceEntity);
    }

    @Override
    public Collection<DeviceEntity> findAll() {
        final String sql = "SELECT * FROM device";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new DeviceEntity(resultSet.getObject("id", UUID.class),
                    resultSet.getString("name"),
                    resultSet.getBoolean("available"),
                    resultSet.getString("useremail"),
                    resultSet.getString("rent"),
                    resultSet.getString("return")
            );
        });
    }

    @Override
    public DeviceEntity save(final DeviceEntity entity) {
        final String sql = "UPDATE device SET available=?, useremail=?, rent=?, return=? WHERE name=?";
        //final String sql = "INSERT INTO device VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, entity.isAvailable(), entity.getEmail(), entity.getRentDate(), entity.getReturnDate(), entity.getName());
        return entity;
    }

    @Override
    public void delete(final DeviceEntity entity) {
        final String sql = "DELETE FROM device WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{entity.getId()});
    }
}
