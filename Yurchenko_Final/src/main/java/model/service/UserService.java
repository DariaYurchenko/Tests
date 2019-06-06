package model.service;

import model.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers();

    User findById(Long id);

    List<User> findByParameter(String column, Object value);

    void registerUser(User user);

    void deleteUserById(Long id);

    void deleteAllUsers();

    void updateUserByLogin(String column, String value, String login);

    Optional<User> findUserByLogin(String login);

    void changeUsersPassword(String hash, byte[] salt, String login);

    void setRank(String login, int plusPoints, int plusMaxPoints);

    List<User> findUsersForPagination(int currentPage, int recordsPerPage);

    void addMagicKey(String magicKey, String login );

    Optional<String> findMagicKey(String login);

    Optional<Integer> findIfSubmit(String login);

    void changeSubmitKeyStatus(String login);
}
