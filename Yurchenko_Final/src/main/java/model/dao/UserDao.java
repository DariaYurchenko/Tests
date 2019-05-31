package model.dao;

import model.entity.User;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    void changePassword(String hash, byte[] salt, String login);

    Map<String, Integer> getUserPointsFromDb(String login);

    Optional<User> findUserByLogin(String login);

    void changeUserRankInDb(String login, Integer plusPoints, Integer plusMaxPoints);

    void updateUserByLogin (String column, Object value, String login);

    Double setUserRank(ResultSet rs);

    void addMagicKey(String magicKey, String login);

    Optional<String> findMagicKey(String login);

    Optional<Integer> findIfSubmit(String login);

    void changeSubmitKeyStatus(String login);

}
