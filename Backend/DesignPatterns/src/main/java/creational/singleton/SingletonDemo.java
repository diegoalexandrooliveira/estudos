package creational.singleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SingletonDemo {

    public static void main(String[] args) throws SQLException {
        DatabaseSingleton instance = DatabaseSingleton.getInstance();

        long timeBefore = 0;
        long timeAfter = 0;

        System.out.println(instance);

        timeBefore = System.currentTimeMillis();
        Connection connection = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);

        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT 1+1");
        if (rs.next()) {
            System.out.println(rs.getInt(1));
            stm.close();
        }
        timeBefore = System.currentTimeMillis();
        connection = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);
    }
}
