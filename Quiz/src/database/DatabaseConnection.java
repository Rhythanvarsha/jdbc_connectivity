package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String url="jdbc:mysql://localhost:3306/QuizApp";
	private static final String username="root";
	 private static final String password="root";
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,username,password);
	}
	public static void closeConnection(Connection con) {
		if(con!=null) {//if connection is open close it by close() method
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error Closing Connection"+e.getMessage());
			}
		}
	}
}
