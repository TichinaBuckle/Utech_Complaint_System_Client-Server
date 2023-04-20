/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package server;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/complaint_system";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(URL);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}