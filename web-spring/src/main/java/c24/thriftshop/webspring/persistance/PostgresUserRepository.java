package c24.thriftshop.webspring.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PostgresUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresUserRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO Crappy implementation dont use empty catch block
    @Override
    public Optional<UserEntity> findByEmail(final String email) {
        final String sql = "SELECT email,password FROM \"user\" WHERE email = ?";
        UserEntity userEntity = null;
        try {
            userEntity = jdbcTemplate.queryForObject(sql, new Object[]{email}, (resultSet, i) -> {
                return new UserEntity(resultSet.getString("email"),
                        resultSet.getString("password")
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
        final String sql = "SELECT email,password FROM \"user\" WHERE id = ?";
        final UserEntity userEntity = jdbcTemplate.queryForObject(sql, new Object[]{uuid}, (resultSet, i) -> {
            return new UserEntity(resultSet.getString("email"),
                    resultSet.getString("password")
            );
        });
        return Optional.ofNullable(userEntity);
    }

    @Override
    public Collection<UserEntity> findAll() {
        final String sql = "SELECT email,password FROM \"user\"";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return new UserEntity(resultSet.getString("email"),
                    resultSet.getString("password")
            );
        });
    }

    @Override
    public UserEntity save(final UserEntity entity) {
        final String sql = "INSERT INTO \"user\" VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, entity.getId(), entity.getEmail(), entity.getPassword(), entity.isActive());
        return entity;
    }

    @Override
    public void delete(final UserEntity entity) {
        final String sql = "DELETE FROM \"user\" WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{entity.getId()});
    }
}
