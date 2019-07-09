package model.dao.impl;

import exception.DaoRuntimeException;
import model.dao.GenericDao;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    private static final Logger LOGGER = Logger.getLogger("GenericDaoImpl.class");

    Connection connection;

    GenericDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public abstract String createQueryToSave();

    public abstract String createQueryToFindById();

    public abstract String createQueryToDelete();

    public abstract String createQueryToDeleteAll();

    public abstract String createQueryToFindAll();

    public abstract String createQueryToPagination();

    public abstract String createQueryToFindByParameter(String column);

    public abstract String createQueryToUpdate(String column);

    public abstract void prepareStatementToSave(PreparedStatement ps, T object);

    public abstract T parseResultSetToFindById(ResultSet rs);

    public abstract List<T> parseResultSet(ResultSet rs);

    @Override
    public void add(T object) {
        String command = createQueryToSave();
        try(PreparedStatement ps = connection.prepareStatement(command)) {
            prepareStatementToSave(ps, object);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with creating object: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String command = createQueryToDelete();
        try(PreparedStatement ps = connection.prepareStatement(command)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with deleting object: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        String command = createQueryToDeleteAll();
        try(Statement st = connection.createStatement()) {
            st.executeUpdate(command);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with deleting all objects: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public List<T> findAll() {
        String command = createQueryToFindAll();
        try(Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(command);
            return parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding all objects: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public Optional<T> findUserById(Integer id) {
        T object = null;
        String command = createQueryToFindById();
        try(PreparedStatement ps = connection.prepareStatement(command)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                object = parseResultSetToFindById(rs);

            }
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding objects by parameter: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
        return Optional.ofNullable(object);
    }

    @Override
    public List<T> findByParameter(String column, Object value) {
        String command = createQueryToFindByParameter(column);
        try(PreparedStatement ps = connection.prepareStatement(command)) {
            ps.setObject(1, value);
            ResultSet rs = ps.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding objects by parameter: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public List<T> findForPagination(int startRecord, int recordsPerPage) {
        String command = createQueryToPagination();
        try (PreparedStatement ps = connection.prepareStatement(command)) {
            ps.setInt(1, startRecord);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding objects for pagination: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public void update(String column, Object value, Integer id) {
        String command = createQueryToUpdate(column);
        try(PreparedStatement ps = connection.prepareStatement(command)) {
            ps.setObject(1, value);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating object: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

}
