package c24.thriftshop.webspring.persistance;

import java.util.Collection;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    Optional<T> findById(ID id);

    Collection<T> findAll();

    T save(T entity);

    void delete(T entity);
}
