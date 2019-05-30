package model.dao.impl;

import exception.DaoException;
import model.dao.UserDao;
import model.dao.connector.Connector;
import model.entity.User;
import model.entity.status.UserStatus;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl(Connector connector) {
        super(connector);
    }

    @Override
    public String createQueryToFindById() {
        return FIND_USER_BY_ID;
    }

    @Override
    public String createQueryToDeleteAll() {
        return DELETE_ALL_USERS;
    }

    @Override
    public String createQueryToDelete() {
        return DELETE_USER;
    }

    @Override
    public String createQueryToSave() {
        return INSERT_USER;
    }

    @Override
    public String createQueryToFindAll() {
        return SELECT_ALL_USERS;
    }

    @Override
    public String createQueryToPagination() {
        return FIND_USERS_FOR_PAGINATION;
    };

    @Override
    public String createQueryToFindByParameter(String column) {
        return String.format(FIND_USER_BY_PARAMETER, column);
    }

    @Override
    public String createQueryToUpdate(String column) {
        return String.format(UPDATE_USER_BY_LOGIN, column);
    }

    @Override
    public void prepareStatementToSave(PreparedStatement ps, User user) {
        try {
            ps.setInt(1, mapRoleToTable(user));
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getHash());
            ps.setBytes(6, user.getSalt());
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for adding user: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private int mapRoleToTable(User user) {
        return user.getStatus().equals(UserStatus.STUDENT) ? 1 : 2;
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) {
        List<User> users = new ArrayList<>();
        try {
            while(rs.next()) {
                users.add(buildUser(rs));
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of user: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Double setUserRank(ResultSet rs) {
        try {
            int userPoints = rs.getInt(USER_POINTS);
            int maxPoints = rs.getInt(MAX_POINTS);
            return getUserRank(userPoints, maxPoints);
        } catch (SQLException e) {
            LOGGER.error("SQLException with setting rank of user: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private Double getUserRank(int userPoints, int maxPoints) {
        return Math.round((userPoints * 100.0 / maxPoints) * 100.0) / 100.0;
    }

    @Override
    public Optional<User> parseResultSetToFindById(ResultSet rs) {
        try {
            return Optional.ofNullable(buildUser(rs));
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of user to find by id: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        User object = null;
        try (PreparedStatement ps = connector.getConnection().prepareStatement(FIND_USER_BY_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(parseResultSetToFindById(rs).isPresent()) {
                    object = parseResultSetToFindById(rs).get();
                }
            }
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding user by login: " + e.getMessage());
            throw new DaoException(e);
        }
        return Optional.ofNullable(object);
    }

    @Override
    public void changePassword(String hash, byte[] salt, String login) {
        try (PreparedStatement ps = connector.getConnection().prepareStatement(UPDATE_USER_PASSWORD)) {
            ps.setString(1, hash);
            ps.setBytes(2, salt);
            ps.setString(3, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for changing password: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Map<String, Integer> getUserPointsFromDb(String login) {
        Map<String, Integer> rank = new HashMap<>();
        int points = 0;
        int maxPoints = 0;
        try (PreparedStatement ps = connector.getConnection().prepareStatement(FIND_USER_BY_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                points = rs.getInt(USER_POINTS);
                maxPoints = rs.getInt(MAX_POINTS);
            }
            rank.put("currentPoints", points);
            rank.put("maxPossiblePoints", maxPoints);
            return rank;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with getting current points: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    /*@Override
    public void changeUserRankInDb(Long id, Integer plusPoints, Integer plusMaxPoints) {
        try (PreparedStatement ps = connector.getConnection().prepareStatement(UPDATE_USER_POINTS)) {
            ps.setInt(1, plusPoints);
            ps.setInt(2, plusMaxPoints);
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating user's points: " + e.getMessage());
            throw new DaoException(e);
        }
    }*/

    @Override
    public void changeUserRankInDb(String login, Integer plusPoints, Integer plusMaxPoints) {
        try (PreparedStatement ps = connector.getConnection().prepareStatement(UPDATE_USER_POINTS)) {
            ps.setInt(1, plusPoints);
            ps.setInt(2, plusMaxPoints);
            ps.setString(3, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating user's points: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    /*@Override
    public List<User> findUsersForPagination(int startRecord, int recordsPerPage) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(FIND_USERS_FOR_PAGINATION)) {
            preparedStatement.setInt(1, startRecord);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                users.add(buildUser(rs));
            }
            return users;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding user for pagination: " + e.getMessage());
            throw new DaoException(e);
        }
    }*/

    @Override
    public void updateUserByLogin(String column, Object value, String login) {
        String command = createQueryToUpdate(column);
        try(PreparedStatement ps = connector.getConnection().prepareStatement(command)) {
            ps.setObject(1, value);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating user by login: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void addMagicKey(String magicKey, String login) {
        updateUserByLogin(MAGIC_KEY, magicKey, login);
        updateUserByLogin(SUBMIT_KEY, NOT_SUBMITTED_KEY, login);
    }

    @Override
    public Optional<String> findMagicKey(String login) {
        String magicKey = null;
        try (PreparedStatement ps = connector.getConnection().prepareStatement(SELECT_USER_MAGIC_KEY)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magicKey = rs.getString(MAGIC_KEY);
            }
            return Optional.ofNullable(magicKey);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding user's magic key: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Integer> findIfSubmit(String login) {
        Integer submit = null;
        try (PreparedStatement ps = connector.getConnection().prepareStatement(SELECT_SUBMIT_TYPE_OF_MAGIC_KEY)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                submit = rs.getInt(SUBMIT_KEY);
            }
            return Optional.ofNullable(submit);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding user by login: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void changeSubmitKeyStatus(String login) {
        try (PreparedStatement ps = connector.getConnection().prepareStatement(UPDATE_USER_SUBMIT_STATUS)) {
            ps.setInt(1, SUBMITTED_KEY);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for changing password: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private User buildUser(ResultSet rs) throws SQLException {
        return new User.Builder()
                .withId(rs.getLong(USER_ID))
                .withUserType(setUserRole(rs))
                .withLogin(rs.getString(USER_LOGIN))
                .withName(rs.getString(USER_NAME))
                .withLastName(rs.getString(USER_LASTNAME))
                .withHash(rs.getString(USER_HASH))
                .withSalt(rs.getBytes(USER_SALT))
                .withRank(setUserRank(rs))
                .build();
    }

    private UserStatus setUserRole(ResultSet rs) {
        try {
            return (rs.getInt(ROLE) == 1) ? UserStatus.STUDENT : UserStatus.ADMIN;
        } catch (SQLException e) {
            LOGGER.error("SQLException with setting role of user: " + e.getMessage());
            throw new DaoException(e);
        }
    }

}
