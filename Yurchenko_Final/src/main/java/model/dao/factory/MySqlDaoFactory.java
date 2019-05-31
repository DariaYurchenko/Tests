package model.dao.factory;

import model.dao.TestDao;
import model.dao.TestInfoDao;
import model.dao.ThemeDao;
import model.dao.UserDao;
import model.dao.impl.*;

public class MySqlDaoFactory extends DaoFactory{
    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public TestDao getTestDao() {
        return new TestDaoImpl();
    }

    @Override
    public ThemeDao getThemeDao() {
        return new ThemeDaoImpl();
    }

    @Override
    public QuestionDaoImpl getQuestionDao() {
        return new QuestionDaoImpl();
    }

    @Override
    public TestInfoDao getTestInfoDao() {
        return new TestInfoDaoImpl();
    }
}
