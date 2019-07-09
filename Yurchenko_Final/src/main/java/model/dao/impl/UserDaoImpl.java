package model.dao.impl;

import exception.DaoRuntimeException;
import model.dao.UserDao;
import model.entity.User;
import model.entity.status.UserStatus;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final String INSERT_USER = "INSERT INTO users(role, login, name, lastname, hash, salt) " +
            "VALUES(?, ?, ?, ?, ?, ?);";
    private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?;";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String FIND_USERS_FOR_PAGINATION = "SELECT * FROM users LIMIT ?, ?;";
    private static final String FIND_USER_BY_PARAMETER = "SELECT * FROM users WHERE %s = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id = ?;";
    private static final String DELETE_ALL_USERS = "DELETE FROM users;";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET hash = ?, salt = ? WHERE login = ?;";
    private static final String UPDATE_USER_POINTS = "UPDATE users SET user_number_of_points = ?, " +
            "user_max_number_of_points = ? WHERE login = ?;";
    private static final String UPDATE_USER_BY_LOGIN = "UPDATE users SET %s = ? WHERE login = ?;";
    private static final String UPDATE_USER_SUBMIT_STATUS = "UPDATE users SET submitted = ? WHERE login = ?;";
    private static final String SELECT_USER_MAGIC_KEY = "SELECT magic_key FROM users WHERE login = ?;";
    private static final String SELECT_SUBMIT_TYPE_OF_MAGIC_KEY = "SELECT submitted FROM users WHERE login = ?;";

    private static final String ROLE = "role";
    private static final String USER_POINTS = "user_number_of_points";
    private static final String MAX_POINTS = "user_max_number_of_points";
    private static final String USER_NAME = "name";
    private static final String USER_LASTNAME = "lastname";
    private static final String USER_ID = "user_id";
    private static final String USER_LOGIN = "login";
    private static final String USER_HASH = "hash";
    private static final String USER_SALT = "salt";
    private static final String MAGIC_KEY = "magic_key";
    private static final String SUBMIT_KEY = "submitted";

    private static final int NOT_SUBMITTED_KEY = 1;
    private static final int SUBMITTED_KEY = 2;

    public UserDaoImpl(Connection connection) {
        super(connection);
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
    }

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
            throw new DaoRuntimeException(e);
        }
    }

    private int mapRoleToTable(User user) {
        return user.getUserStatus().equals(UserStatus.STUDENT) ? 1 : 2;
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
            throw new DaoRuntimeException(e);
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
            throw new DaoRuntimeException(e);
        }
    }

    private Double getUserRank(int userPoints, int maxPoints) {
        return Math.round((userPoints * 100.0 / maxPoints) * 100.0) / 100.0;
    }

    @Override
    public User parseResultSetToFindById(ResultSet rs) {
        try {
            return buildUser(rs);
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of user to find by id: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        User object = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                object = parseResultSetToFindById(rs);
            }
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding user by login: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
        return Optional.ofNullable(object);
    }

    @Override
    public void changePassword(String hash, byte[] salt, String login) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            ps.setString(1, hash);
            ps.setBytes(2, salt);
            ps.setString(3, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for changing password: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public Map<String, Integer> getUserPointsFromDb(String login) {
        Map<String, Integer> rank = new HashMap<>();
        int points = 0;
        int maxPoints = 0;
        try (PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_LOGIN)) {
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
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public void changeUserRankInDb(String login, int plusPoints, int plusMaxPoints) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_POINTS)) {
            ps.setInt(1, plusPoints);
            ps.setInt(2, plusMaxPoints);
            ps.setString(3, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating user's points: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public void updateUserByLogin(String column, Object value, String login) {
        String command = createQueryToUpdate(column);
        try(PreparedStatement ps = connection.prepareStatement(command)) {
            ps.setObject(1, value);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating user by login: " + e.getMessage());
            throw new DaoRuntimeException(e);
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
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_MAGIC_KEY)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                magicKey = rs.getString(MAGIC_KEY);
            }
            return Optional.ofNullable(magicKey);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding user's magic key: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public Optional<Integer> findIfSubmit(String login) {
        Integer submit = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_SUBMIT_TYPE_OF_MAGIC_KEY)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                submit = rs.getInt(SUBMIT_KEY);
            }
            return Optional.ofNullable(submit);
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding user by login: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    @Override
    public void changeSubmitKeyStatus(String login) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_SUBMIT_STATUS)) {
            ps.setInt(1, SUBMITTED_KEY);
            ps.setString(2, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for changing password: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    private User buildUser(ResultSet rs) throws SQLException {
        return new User.Builder()
                .withId(rs.getInt(USER_ID))
                .withUserType(setUserRole(rs))
                .withLogin(rs.getString(USER_LOGIN))
                .withName(rs.getString(USER_NAME))
                .withLastName(rs.getString(USER_LASTNAME))
                .withHash(rs.getString(USER_HASH))
                .withSalt(rs.getBytes(USER_SALT))
                .withRank(setUserRank(rs))
                .build();
    }

    private UserStatus setUserRole(ResultSet rs) throws SQLException {
        return (rs.getInt(ROLE) == 1) ? UserStatus.STUDENT : UserStatus.ADMIN;
    }

}
