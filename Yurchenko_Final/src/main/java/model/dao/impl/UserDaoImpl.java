package model.dao.impl;

import exception.DaoException;
import model.dao.UserDao;
import model.dao.connector.Connector;
import model.entity.User;
import model.entity.entityenum.UserType;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


//TODO: вся логика в сервисах
//TODO: джойны в репозитория на юзер/роль
//TODO: фильтры + индексация???3
//TODO: mapper entity
//TODO: think about amount of varchar (45 to much) + госты для пароля и имейла.
//can save roles in db like strings and map them
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
    public String createQueryToAdd() {
        return INSERT_USER;
    }

    @Override
    public String createQueryToFindAll() {
        return SELECT_ALL_USERS;
    }

    @Override
    public String createQueryToFindByParameter(String column) {
        return "SELECT * FROM users WHERE " + column + " = ?";
    }

    //TODO: constants StringFormat
    @Override
    public String createQueryToUpdate(String column) {
        return "UPDATE users SET " + column + " = ? WHERE user_id = ?;";
    }

    @Override
    public void prepareStatementToAdd(PreparedStatement ps, User user) {
        try {
            ps.setInt(1, mapRoleToTable(user));
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getHash());
            ps.setBytes(6, user.getSalt());
        } catch (SQLException e) {
            LOGGER.error("SQLException with preparing statement for creating user: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private int mapRoleToTable(User user) {
        return user.getType().equals(UserType.STUDENT) ? 1 : 2;
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) {
        List<User> users = new ArrayList<>();
        try {
            while(rs.next()) {
                users.add(( new User.Builder()
                        .setId(rs.getLong(1))
                        .setUserType(setUserRole(rs))
                        .setLogin(rs.getString(3))
                        .setName(rs.getString(4))
                        .setLastName(rs.getString(5))
                        .setHash(rs.getString(6))
                        .setSalt(rs.getBytes(7))
                        .setRank(setUserRank(rs))
                        .build()));
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of user: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    //TODO: separate method
    public Double setUserRank(ResultSet rs) {
        try {
            int userPoints = rs.getInt(USER_POINTS);
            int maxPoints = rs.getInt(MAX_POINTS);
            return Math.round((userPoints * 100.0 / maxPoints) * 100.0) / 100.0;
        } catch (SQLException e) {
            LOGGER.error("SQLException with setting rank of user: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    private UserType setUserRole(ResultSet rs) {
        try {
            return (rs.getInt(ROLE) == 1) ? UserType.STUDENT : UserType.ADMIN;
        } catch (SQLException e) {
            LOGGER.error("SQLException with setting role of user: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    //TODO: bring all constants to UserDao
    //TODO: utf8
    @Override
    public Optional<User> parseResultSetToFindById(ResultSet rs) {
        try {
            return Optional.ofNullable(new User.Builder()
                    .setId(rs.getLong(USER_ID))
                    .setUserType(setUserRole(rs))
                    .setLogin(rs.getString(USER_LOGIN))
                    .setName(rs.getString(USER_NAME))
                    .setLastName(rs.getString(USER_LASTNAME))
                    .setHash(rs.getString(USER_HASH))
                    .setSalt(rs.getBytes(USER_SALT))
                    .setRank(setUserRank(rs))
                    .build());
        } catch (SQLException e) {
            LOGGER.error("SQLException with parsing resultset of user: " + e.getMessage());
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
    public Map<String, Integer> getCurrentPoints(Long id) {
        Map<String, Integer> rank = new HashMap<>();
        int points = 0;
        int maxPoints = 0;
        try (PreparedStatement ps = connector.getConnection().prepareStatement(FIND_USER_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                points = rs.getInt(USER_POINTS);
                maxPoints = rs.getInt(MAX_POINTS);
            }
            rank.put("currentPoints", points);
            rank.put("maxCurrentPoints", maxPoints);
            return rank;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with getting current points: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    public void changeRank(Long id, Integer plusPoints, Integer plusMaxPoints) {
        try (PreparedStatement ps = connector.getConnection().prepareStatement(UPDATE_USER_POINTS)) {
            ps.setInt(1, plusPoints);
            ps.setInt(2, plusMaxPoints);
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("SQLException with updating user's points: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findUsersForPagination(int currentPage, int recordsPerPage) throws DaoException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connector.getConnection().prepareStatement(FIND_USERS_FOR_PAGINATION)) {
            preparedStatement.setInt(1, currentPage);
            preparedStatement.setInt(2, recordsPerPage);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                users.add(( new User.Builder()
                        .setId(rs.getLong(USER_ID))
                        .setUserType(setUserRole(rs))
                        .setLogin(rs.getString(USER_LOGIN))
                        .setName(rs.getString(USER_NAME))
                        .setLastName(rs.getString(USER_LASTNAME))
                        .setHash(rs.getString(USER_HASH))
                        .setSalt(rs.getBytes(USER_SALT))
                        .setRank(setUserRank(rs))
                        .build()));
                }
            return users;
        } catch (SQLException e) {
            LOGGER.warn("SQLException with finding for pagination: " + e.getMessage());
            throw new DaoException(e);
        }
    }
}
