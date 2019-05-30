package model.service.impl;

import exception.DaoException;
import exception.ServiceException;
import model.dao.UserDao;
import model.dao.connector.Connector;
import model.dao.impl.UserDaoImpl;
import model.entity.User;
import model.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl(new Connector());
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        return userDao.findById(userId).orElseThrow(ServiceException::new);
    }

    @Override
    public List<User> findUsersByParameter(String column, Object value) {
        return userDao.findByParameter(column, value);
    }

    @Override
    public void registerUser(User user) {
        userDao.add(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        userDao.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAll();
    }

    @Override
    public void updateUserByLogin(String column, String value, String login) {
        userDao.updateUserByLogin(column, value, login);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    @Override
    public void changeUsersPassword(String hash, byte[] salt, String login) {
        userDao.changePassword(hash, salt, login);
    }

    @Override
    public void setRank(String login, Integer plusPoints, Integer plusMaxPoints) {
        Map<String, Integer> startRank = userDao.getUserPointsFromDb(login);

        Integer newPoints = changeUserPoints(startRank.get("currentPoints"), plusPoints);
        Integer newMaxPoints = changeUserPoints(startRank.get("maxPossiblePoints"), plusMaxPoints);

        userDao.changeUserRankInDb(login, newPoints, newMaxPoints);
    }

    private Integer changeUserPoints(Integer startPoints, Integer plusPoints) {
        return startPoints + plusPoints;
    }

    @Override
    public List<User> findUsersForPagination(int currentPage, int recordsPerPage) throws DaoException {
        return userDao.findForPagination(currentPage, recordsPerPage);
    }

    @Override
    public void addKey(String magicKey, String login ) {
        userDao.addMagicKey(magicKey, login);
    }

    @Override
    public String findMagicKey(String login) {
        return userDao.findMagicKey(login).orElseThrow(ServiceException::new);
    }

    @Override
    public Optional<Integer> findIfSubmit(String login) {
        return userDao.findIfSubmit(login);
    }

    @Override
    public void changeSubmitKeyStatus(String login) {
        userDao.changeSubmitKeyStatus(login);
    }

}
