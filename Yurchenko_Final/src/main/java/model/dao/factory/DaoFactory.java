package model.dao.factory;

import exception.DaoException;
import model.dao.*;

public abstract class DaoFactory {
    public abstract UserDao getUserDao();

    public abstract TestDao getTestDao();

    public abstract ThemeDao getThemeDao();

    public abstract QuestionDao getQuestionDao();

    public abstract TestInfoDao getTestInfoDao();

    public static DaoFactory getDAOFactory(DbNames dbNames) {
        switch (dbNames) {
            case MYSQL:
                return new MySqlDaoFactory();
            default:
                throw new DaoException();
        }
    }
}
