package model.dao;

import model.entity.User;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    String INSERT_USER = "INSERT INTO users(role, login, name, lastname, hash, salt) " +
            "VALUES(?, ?, ?, ?, ?, ?);";
    String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?;";
    String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    String FIND_USERS_FOR_PAGINATION = "SELECT * FROM users LIMIT ?, ?;";
    String FIND_USER_BY_PARAMETER = "SELECT * FROM users WHERE %s = ?";;
    String SELECT_ALL_USERS = "SELECT * FROM users;";
    String DELETE_USER = "DELETE FROM users WHERE user_id = ?;";
    String DELETE_ALL_USERS = "DELETE FROM users;";
    String UPDATE_USER_PASSWORD = "UPDATE users SET hash = ?, salt = ? WHERE login = ?;";
    /*static final String UPDATE_USER_POINTS = "UPDATE users SET user_number_of_points = ?, " +
            "user_max_number_of_points = ? WHERE user_id = ?;";*/
    String UPDATE_USER_POINTS = "UPDATE users SET user_number_of_points = ?, " +
            "user_max_number_of_points = ? WHERE login = ?;";
    String UPDATE_USER_BY_LOGIN = "UPDATE users SET %s = ? WHERE login = ?;";
    String UPDATE_USER_SUBMIT_STATUS = "UPDATE users SET submitted = ? WHERE login = ?;";
    String SELECT_USER_MAGIC_KEY = "SELECT magic_key FROM users WHERE login = ?;";
    String SELECT_SUBMIT_TYPE_OF_MAGIC_KEY = "SELECT submitted FROM users WHERE login = ?;";

    String ROLE = "role";
    String USER_POINTS = "user_number_of_points";
    String MAX_POINTS = "user_max_number_of_points";
    String USER_NAME = "name";
    String USER_LASTNAME = "lastname";
    String USER_ID = "user_id";
    String USER_LOGIN = "login";
    String USER_HASH = "hash";
    String USER_SALT = "salt";
    String MAGIC_KEY = "magic_key";
    String SUBMIT_KEY = "submitted";

    int NOT_SUBMITTED_KEY = 1;
    int SUBMITTED_KEY = 2;

    void changePassword(String hash, byte[] salt, String login);

    Map<String, Integer> getUserPointsFromDb(String login);

    Optional<User> findUserByLogin(String login);

    //void changeUserRankInDb(Long id, Integer plusPoints, Integer plusMaxPoints);

    void changeUserRankInDb(String login, Integer plusPoints, Integer plusMaxPoints);

    //List<User> findUsersForPagination(int currentPage, int recordsPerPage);

    void updateUserByLogin (String column, Object value, String login);

    Double setUserRank(ResultSet rs);

    void addMagicKey(String magicKey, String login);

    Optional<String> findMagicKey(String login);

    Optional<Integer> findIfSubmit(String login);

    void changeSubmitKeyStatus(String login);

}
