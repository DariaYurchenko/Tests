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

    public GenericDaoImpl(Connector connector) {
        this.connector = connector;
    }

    public abstract String createQueryToAdd();

    public abstract String createQueryToFindById();

    public abstract String createQueryToDelete();

    public abstract String createQueryToDeleteAll();

    public abstract String createQueryToFindAll();

    public abstract String createQueryToFindByParameter(String column);

    public abstract String createQueryToUpdate(String column);

    public abstract void prepareStatementToAdd(PreparedStatement ps, T object);

    public abstract T parseResultSetToFindById(ResultSet rs);

    public abstract List<T> parseResultSet(ResultSet rs);

    //public abstract String getTableName();

    @Override
    public void create(T object) {
        String command = createQueryToAdd();
        try(PreparedStatement ps = connector.getConnection().prepareStatement(command)) {
            prepareStatementToAdd(ps, object);
            int count = ps.executeUpdate();
            //TODO: if should do such check in dao
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

    @Override
    public Optional<T> findById(Long id) {
        T object = null;
        String command = createQueryToFindById();
        try(PreparedStatement ps = connector.getConnection().prepareStatement(command)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                object = parseResultSetToFindById(rs);
            }
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding objects by parameter: " + e.getMessage());
            throw new DaoException(e);
        }
        return Optional.ofNullable(object);
    }






}
