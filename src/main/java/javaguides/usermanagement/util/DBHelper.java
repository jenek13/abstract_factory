package javaguides.usermanagement.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static Connection dbConnection = null;

    public static Connection getMysqlConnection() {
        if (dbConnection != null) {
            return dbConnection;
        } else {
            try {
                InputStream inputStream = DBHelper.class.getClassLoader()
                        .getResourceAsStream("db.properties");
                Properties properties = new Properties();
                if (properties != null) {
                    properties.load(inputStream);

                    String dbDriver = properties.getProperty("dbDriver");
                    String connectionUrl = properties
                            .getProperty("connectionUrl");
                    String userName = properties.getProperty("userName");
                    String password = properties.getProperty("password");

                    Class.forName(dbDriver).newInstance();
                    dbConnection = DriverManager.getConnection(connectionUrl,
                            userName, password);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dbConnection;
        }
    }
}
