
//In this program we will connect to the database
import java.sql.*;
import java.util.Scanner;

public class Connect {

	public static void main(String[] args) {
		String username;
		String password;
		
		//This code does not work yet
		Class.forName("JDBCDriverClass");
		Connection connection = DriverManager.getConnection
				("jdbc:mysql://localhost/javaproject", username, password );
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery();

		connection.close();
	}
	
	

}
