package model.dao.connector;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import exception.ConnectorException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Connector {
    private static final Logger LOGGER = Logger.getLogger(Connector.class);
    private Connection connection;

    //private static ComboPooledDataSource cpds = new ComboPooledDataSource();
/*
   static {
        try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/tests?autoReconnect=true&useSSL=false");
            cpds.setUser("root");
            cpds.setPassword("1111");
        } catch (PropertyVetoException e) {
            // handle the exception
        }
    }

    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }*/


    //private volatile DataSource dataSource;

    public BasicDataSource getDataSource() throws IOException {

        Properties property = new Properties();
        BasicDataSource ds = new BasicDataSource();

        try {
            property.load(getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties"));

            ds.setUrl(property.getProperty("jdbc.url"));
            ds.setUsername(property.getProperty("jdbc.username"));
            ds.setPassword(property.getProperty("jdbc.password"));

            ds.setMinIdle(5);
            ds.setMaxIdle(10);
            ds.setMaxOpenPreparedStatements(100);

        } catch (IOException e) {
            e.printStackTrace(); //TODO logger
        }
        return ds;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }



  /* public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("db/db.properties"));

            String driverDb = properties.getProperty("mysql.driver");
            String connectToDb = properties.getProperty("mysql.url");
            String loginToDb = properties.getProperty("mysql.user.name");
            String passwordToDb = properties.getProperty("mysql.password");

            Class.forName(driverDb);
            return connection = DriverManager.getConnection(connectToDb, loginToDb, passwordToDb);
        } catch (SQLException e) {
            LOGGER.fatal("SQLException. Couldn't connect to database.");
            throw new RuntimeException(e);
        }catch (IOException e) {
            LOGGER.fatal("IOException. Couldn't connect to database.");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            LOGGER.fatal("ClassNotFoundException. Couldn't connect to database.");
            throw new RuntimeException(e);
        }
    }*/

  /*  public Connection getConnection() {
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
                        properties.load(getClass().getClassLoader().getResourceAsStream("db/db.properties"));

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
    }*/

}
