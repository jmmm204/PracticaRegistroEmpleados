package repository;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T, ID> {
    void create(T entity);
    Optional<T> getById(ID id);
    List<T> getAll();
    void update(T entity);
    void delete(ID id);
}