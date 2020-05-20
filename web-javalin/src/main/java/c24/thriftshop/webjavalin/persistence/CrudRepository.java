package c24.thriftshop.webjavalin.persistence;

import java.util.Optional;

public interface CrudRepository<T, ID> {
    long count();

    void delete(T entity);

    void deleteById(ID id);

    boolean existsById(ID id);

    Iterable<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);
}
