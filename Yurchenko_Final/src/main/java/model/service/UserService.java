package model.service;

import model.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findUserById(Long id);

    List<User> findUsersByParameter(String column, Object value);

    void registerUser(User user);

    void deleteUserById(Long id);

    void deleteAllUsers();

    void updateUserByLogin(String column, String value, String login);

    Optional<User> findUserByLogin(String login);

    void changeUsersPassword(String hash, byte[] salt, String login);

    void setRank(String login, Integer plusPoints, Integer plusMaxPoints);

    List<User> findUsersForPagination(int currentPage, int recordsPerPage);

    void addKey(String magicKey, String login );

    String findMagicKey(String login);

    Optional<Integer> findIfSubmit(String login);

    void changeSubmitKeyStatus(String login);
}
