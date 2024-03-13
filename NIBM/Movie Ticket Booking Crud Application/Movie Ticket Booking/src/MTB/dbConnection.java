
package MTB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cinema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private Statement statement;

    public dbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error in database connection:");
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error in query execution:");
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection:");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
