package model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    void create(T object);

    void deleteById(Long id);

    public void deleteAll();

    void update(String column, Object value, Long id);

    List<T> findAll();

    List<T> findByParameter(String column, Object value);

    Optional<T> findById(Long id);
}
