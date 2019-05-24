package model.service.impl;

import exception.DaoException;
import model.dao.UserDao;
import model.dao.connector.Connector;
import model.dao.impl.UserDaoImpl;
import model.entity.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService {
   // private static final Logger logger = Logger.getLogger(UserService.class);

    private UserDao userDao;
    private Connector connector = new Connector();

    public UserService() {
        this.userDao = new UserDaoImpl(connector);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userDao.findById(id);
    }

    public List<User> findUsersByParameter(String column, Object value) {
        return userDao.findByParameter(column, value);
    }

    public void registerUser(User user) {
        userDao.create(user);
    }

    public void deleteUserById(Long id) {
        userDao.deleteById(id);
    }

    public void deleteAllUsers() {
        userDao.deleteAll();
    }

    public void updateUserById(String column, String value, Long id) {
        userDao.update(column, value, id);
    }

    public Optional<User> findUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    public void changeUsersPassword(String hash, byte[] salt, String login) {
        userDao.changePassword(hash, salt, login);
    }

    public void setRank(Long id, Integer plusPoints, Integer plusMaxPoints) {
        Map<String, Integer> startRank = userDao.getCurrentPoints(id);

        Integer newPoints = changePoints(startRank.get("currentPoints"), plusPoints);
        Integer newMaxPoints = changePoints(startRank.get("maxCurrentPoints"), plusMaxPoints);

        userDao.changeRank(id, newPoints, newMaxPoints);
    }

    private Integer changePoints(Integer startPoints, Integer plusPoints) {
        return startPoints + plusPoints;
    }

    public List<User> findUsersForPagination(int currentPage, int recordsPerPage) throws DaoException {
        return userDao.findUsersForPagination(currentPage, recordsPerPage);
    }

}
