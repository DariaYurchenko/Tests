package model.dao.connector;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static final Logger LOGGER = Logger.getLogger(Connector.class);
    private Connection connection;

    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("dao/db.properties"));

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
    }

}
