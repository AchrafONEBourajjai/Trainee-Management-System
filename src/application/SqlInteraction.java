package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlInteraction {
	static Connection con = null;
	public static Connection getConnection () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/MTrainee";
			con = DriverManager.getConnection(url,"root","");
			System.out.println("connected");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
