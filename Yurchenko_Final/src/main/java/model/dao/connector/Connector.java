package model.dao.connector;

import exception.DaoRuntimeException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class Connector {
    private static final Logger LOGGER = Logger.getLogger(Connector.class);
    private static volatile Connector instance;

    private Connector() {
    }

    public static Connector getInstance() {
        Connector connectorInstance = instance;
        if (connectorInstance == null) {
            synchronized (Connector.class) {
                connectorInstance = instance;
                if (instance == null) {
                    connectorInstance =  new Connector();
                    instance = connectorInstance;
                }
            }
        }
        return connectorInstance;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            LOGGER.error("IOException while getting connection: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

    private BasicDataSource getDataSource() {
        Properties property = new Properties();
        BasicDataSource ds = new BasicDataSource();
        return setUpConnection(property, ds);
    }

    private BasicDataSource setUpConnection(Properties property, BasicDataSource ds) {
        try {
            property.load(Connector.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties"));

            ds.setUrl(property.getProperty("jdbc.url"));
            ds.setUsername(property.getProperty("jdbc.username"));
            ds.setPassword(property.getProperty("jdbc.password"));

            ds.setMinIdle(Integer.parseInt(property.getProperty("min.Idle")));
            ds.setMaxIdle(Integer.parseInt(property.getProperty("max.Idle")));
            ds.setMaxOpenPreparedStatements(Integer.parseInt(property.getProperty("max.prep.stat")));

            return ds;
        } catch (IOException e) {
            LOGGER.error("IOException while getting data source: " + e.getMessage());
            throw new DaoRuntimeException(e);
        }
    }

}
