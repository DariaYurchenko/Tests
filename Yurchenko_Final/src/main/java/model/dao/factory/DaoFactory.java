package model.dao.factory;

import exception.DaoRuntimeException;
import model.dao.UserDao;
import model.dao.TestDao;
import model.dao.ThemeDao;
import model.dao.QuestionDao;
import model.dao.TestInfoDao;

public abstract class DaoFactory {
    public abstract UserDao getUserDao();

    public abstract TestDao getTestDao();

    public abstract ThemeDao getThemeDao();

    public abstract QuestionDao getQuestionDao();

    public abstract TestInfoDao getTestInfoDao();

    public static DaoFactory getDAOFactory(DbNames dbNames) {
        if (dbNames == DbNames.MYSQL) {
            return new MySqlDaoFactory();
        }
        throw new DaoRuntimeException();
    }
}
