package creational.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseSingleton {

    private volatile Connection connection = null;

    private static volatile DatabaseSingleton instance = null;

    private DatabaseSingleton() {
        if (connection != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }

        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }

    }

    public static DatabaseSingleton getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (DatabaseSingleton.class) {
                if (Objects.isNull(instance)) {
                    instance = new DatabaseSingleton();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection == null) {
            synchronized (DatabaseSingleton.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection("jdbc:h2:mem:");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }


}
