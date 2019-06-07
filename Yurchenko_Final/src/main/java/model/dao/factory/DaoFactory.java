package model.dao.factory;

import exception.DaoRuntimeException;
import model.dao.UserDao;
import model.dao.TestDao;
import model.dao.ThemeDao;
import model.dao.QuestionDao;
import model.dao.TestInfoDao;

public abstract class DaoFactory {
    private static volatile DaoFactory instance;

    public static DaoFactory getInstance(DbNames nameDb) {
        DaoFactory daoFactoryInstance = instance;
        if (daoFactoryInstance == null) {
            synchronized (DaoFactory.class) {
                daoFactoryInstance = instance;
                if (instance == null) {
                    switch (nameDb) {
                        case MYSQL:
                            daoFactoryInstance = new MySqlDaoFactory();
                            break;
                        default:
                            daoFactoryInstance = null;
                    }
                    instance = daoFactoryInstance;
                }
            }
        }
        return daoFactoryInstance;
    }

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
