package model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

    void add(T object);

    void deleteById(Integer id);

    void deleteAll();

    void update(String column, Object value, Integer id);

    List<T> findAll();

    List<T> findForPagination(int startRecord, int recordsPerPage);

    List<T> findByParameter(String column, Object value);

    Optional<T> findUserById(Integer id);
}
