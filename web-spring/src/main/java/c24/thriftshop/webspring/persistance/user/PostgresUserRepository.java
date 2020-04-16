package c24.thriftshop.webspring.persistance.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository("userPostgres")
public class PostgresUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresUserRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserEntity> findByEmail(final String email) {
        final String sql = "SELECT * FROM \"user\" WHERE email = ? AND isactive = true";
        UserEntity userEntity = null;
        try {
            userEntity = jdbcTemplate.queryForObject(sql, new Object[]{email.toLowerCase()}, (resultSet, i) -> {
                return new UserEntity(resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("salt"),
                        resultSet.getObject("id", UUID.class),
                        resultSet.getBoolean("isactive")
                );
            });
        } catch (final Exception ex) {

        }

        return Optional.ofNullable(userEntity);
    }

    @Override
    public void softDelete(final UserEntity entity) {
        final String sql = "UPDATE \"user\" SET isActive=false WHERE id=?";
        jdbcTemplate.update(sql, entity.getId());
    }

    @Override
    public Optional<UserEntity> findById(final UUID uuid) {
        final String sql = "SELECT * FROM \"user\" WHERE id = ? AND isactive = true";
        final UserEntity userEntity = jdbcTemplate.queryForObject(sql, new Object[]{uuid}, (resultSet, i) -> {
            return new UserEntity(resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("salt"),
                    resultSet.getObject("id", UUID.class),
                    resultSet.getBoolean("isactive")
            );
        });
        return Optional.ofNullable(userEntity);
    }

    @Override
    public Collection<UserEntity> findAll() {
        final String sql = "SELECT * FROM \"user\"";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new UserEntity(resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("salt"),
                    resultSet.getObject("id", UUID.class),
                    resultSet.getBoolean("isactive")
            );
        });
    }

    @Override
    public UserEntity save(final UserEntity entity) {
        final String sql = "INSERT INTO \"user\" VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, entity.getId(), entity.getEmail(), entity.getPassword(), entity.isActive(), entity.getSalt());
        return entity;
    }

    @Override
    public void delete(final UserEntity entity) {
        final String sql = "DELETE FROM \"user\" WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{entity.getId()});
    }
}
