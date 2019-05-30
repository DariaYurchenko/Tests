package model.dao.connector;

import exception.ConnectorException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class Connector {
    private static final Logger LOGGER = Logger.getLogger(Connector.class);
    private volatile DataSource dataSource;

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            LOGGER.fatal("SQLException. Couldn't connect to database: " + e.getMessage());
            throw new ConnectorException(e);
        }
    }

    private DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (Connector.class) {
                try {
                    if (dataSource == null) {
                        Properties properties = new Properties();
                        properties.load(getClass().getClassLoader().getResourceAsStream("dao/db.properties"));

                        //TODO:to constants
                        String driverDb = properties.getProperty("mysql.driver");
                        String connectToDb = properties.getProperty("mysql.url");
                        String loginToDb = properties.getProperty("mysql.user.name");
                        String passwordToDb = properties.getProperty("mysql.password");
                        int minIdle = Integer.parseInt(properties.getProperty("mysql.min"));
                        int maxIdle = Integer.parseInt(properties.getProperty("mysql.max"));
                        int maxPrepStat = Integer.parseInt(properties.getProperty("mysql.max.open.prepared.statements"));

                        BasicDataSource ds = new BasicDataSource();
                        ds.setDriverClassName(driverDb);
                        ds.setUrl(connectToDb);
                        ds.setUsername(loginToDb);
                        ds.setPassword(passwordToDb);
                        ds.setMinIdle(minIdle);
                        ds.setMaxIdle(maxIdle);
                        ds.setMaxOpenPreparedStatements(maxPrepStat);
                        dataSource = ds;
                    }
                } catch (IOException e) {
                    LOGGER.fatal("IOException while connecting to database: " + e.getMessage());
                    throw new ConnectorException(e);
                }
            }
        }
        return dataSource;
    }

}
