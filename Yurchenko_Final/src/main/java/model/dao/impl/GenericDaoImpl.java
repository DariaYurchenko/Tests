package model.dao.impl;

import exception.DaoException;
import model.dao.GenericDao;
import model.dao.connector.Connector;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    private static final Logger LOGGER = Logger.getLogger("GenericDaoImpl.class");

    protected Connector connector;

    public GenericDaoImpl() {
        this.connector = new Connector();
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

    public abstract Optional<T> parseResultSetToFindById(ResultSet rs);

    public abstract List<T> parseResultSet(ResultSet rs);

    @Override
    public void add(T object) {
        String command = createQueryToSave();
        try(PreparedStatement ps = connector.getConnection().prepareStatement(command)) {
            prepareStatementToSave(ps, object);
            int count = ps.executeUpdate();
            //TODO: if should do such check in db
            if(count != 1) {
                LOGGER.warn("More then one sign was added.");
                throw new DaoException("More then one sign was added.");
            }
        } catch (SQLException e) {
            LOGGER.warn("SQLException with creating object: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String command = createQueryToDelete();
        try(PreparedStatement ps = connector.getConnection().prepareStatement(command)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with deleting object: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteAll() {
        String command = createQueryToDeleteAll();
        try(Statement st = connector.getConnection().createStatement()) {
            st.executeUpdate(command);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with deleting all objects: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<T> findAll() {
        String command = createQueryToFindAll();
        try(Statement st = connector.getConnection().createStatement()) {
            ResultSet rs = st.executeQuery(command);
            return parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding all objects: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        T object = null;
        String command = createQueryToFindById();
        try(PreparedStatement ps = connector.getConnection().prepareStatement(command)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                if(parseResultSetToFindById(rs).isPresent()) {
                    object = parseResultSetToFindById(rs).get();
                }
            }
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding objects by parameter: " + e.getMessage());
            throw new DaoException(e);
        }
        return Optional.ofNullable(object);
    }

    @Override
    public List<T> findByParameter(String column, Object value) {
        String command = createQueryToFindByParameter(column);
        try(PreparedStatement ps = connector.getConnection().prepareStatement(command)) {
            ps.setObject(1, value);
            ResultSet rs = ps.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding objects by parameter: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<T> findForPagination(int startRecord, int recordsPerPage) {
        String command = createQueryToPagination();
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(command)) {
            preparedStatement.setInt(1, startRecord);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet rs = preparedStatement.executeQuery();
            return parseResultSet(rs);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding objects for pagination: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void update(String column, Object value, Long id) {
        String command = createQueryToUpdate(column);
        try(PreparedStatement ps = connector.getConnection().prepareStatement(command)) {
            ps.setObject(1, value);
            ps.setLong(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating object: " + e.getMessage());
            throw new DaoException(e);
        }
    }
}
