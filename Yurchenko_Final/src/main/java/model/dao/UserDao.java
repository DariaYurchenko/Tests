package model.dao;

import model.entity.User;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    static final String INSERT_USER = "INSERT INTO users(role, login, name, lastname, hash, salt) " +
            "VALUES(?, ?, ?, ?, ?, ?);";
    static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?;";
    static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    static final String FIND_USERS_FOR_PAGINATION = "SELECT * FROM users LIMIT ?, ?;";
    static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    static final String DELETE_USER = "DELETE FROM users WHERE user_id = ?;";
    static final String DELETE_ALL_USERS = "DELETE FROM users;";
    static final String UPDATE_USER_PASSWORD = "UPDATE users SET hash = ?, salt = ? WHERE login = ?;";
    static final String UPDATE_USER_POINTS = "UPDATE users SET user_number_of_points = ?, " +
            "user_max_number_of_points = ? WHERE user_id = ?;";

    static final String ROLE = "role";
    static final String USER_POINTS = "user_number_of_points";
    static final String MAX_POINTS = "user_max_number_of_points";
    static final String USER_NAME = "name";
    static final String USER_LASTNAME = "lastname";
    static final String USER_ID = "user_id";
    static final String USER_LOGIN = "login";
    static final String USER_HASH = "hash";
    static final String USER_SALT = "salt";

    void changePassword(String hash, byte[] salt, String login);

    Map<String, Integer> getCurrentPoints(Long id);

    Optional<User> findUserByLogin(String login);

    void changeRank(Long id, Integer plusPoints, Integer plusMaxPoints);

    List<User> findUsersForPagination(int currentPage, int recordsPerPage);

    Double setUserRank(ResultSet rs);

    void addMagicKey(String magicKey, String login);

    String getMagicKey(String login);

    int getSubmit(String login);

}
