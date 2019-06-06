package model.dao.factory;

import model.dao.TestDao;
import model.dao.TestInfoDao;
import model.dao.ThemeDao;
import model.dao.UserDao;
import model.dao.connector.Connector;
import model.dao.impl.UserDaoImpl;
import model.dao.impl.TestDaoImpl;
import model.dao.impl.TestInfoDaoImpl;
import model.dao.impl.ThemeDaoImpl;
import model.dao.impl.QuestionDaoImpl;
import java.sql.Connection;

public class MySqlDaoFactory extends DaoFactory{
    private Connection connection;

    MySqlDaoFactory() {
        this.connection = Connector.getInstance().getConnection();
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl(connection);
    }

    @Override
    public TestDao getTestDao() {
        return new TestDaoImpl(connection);
    }

    @Override
    public ThemeDao getThemeDao() {
        return new ThemeDaoImpl(connection);
    }

    @Override
    public QuestionDaoImpl getQuestionDao() {
        return new QuestionDaoImpl(connection);
    }

    @Override
    public TestInfoDao getTestInfoDao() {
        return new TestInfoDaoImpl(connection);
    }
}
