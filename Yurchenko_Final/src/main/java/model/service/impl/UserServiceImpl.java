package model.service.impl;

import exception.ServiceRuntimeException;
import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.factory.DbNames;
import model.entity.User;
import model.service.UserService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao =  DaoFactory.getDAOFactory(DbNames.MYSQL).getUserDao();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findUserById(Integer userId) {
        return userDao.findUserById(userId).orElseThrow(ServiceRuntimeException::new);
    }

    @Override
    public List<User> findUserByParameter(String column, Object value) {
        return userDao.findByParameter(column, value);
    }

    @Override
    public void registerUser(User user) {
        userDao.add(user);
    }

    @Override
    public void deleteUserById(Integer userId) {
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
    public void setRank(String login, int plusPoints, int plusMaxPoints) {
        Map<String, Integer> startRank = userDao.getUserPointsFromDb(login);

        int newPoints = changeUserPoints(startRank.get("currentPoints"), plusPoints);
        int newMaxPoints = changeUserPoints(startRank.get("maxPossiblePoints"), plusMaxPoints);

        userDao.changeUserRankInDb(login, newPoints, newMaxPoints);
    }

    private int changeUserPoints(int startPoints, int plusPoints) {
        return startPoints + plusPoints;
    }

    @Override
    public List<User> findUsersForPagination(int currentPage, int recordsPerPage) {
        return userDao.findForPagination(currentPage, recordsPerPage);
    }

    @Override
    public void addMagicKey(String magicKey, String login ) {
        userDao.addMagicKey(magicKey, login);
    }

    @Override
    public Optional<String> findMagicKey(String login) {
        return userDao.findMagicKey(login);
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
