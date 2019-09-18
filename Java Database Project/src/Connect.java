
/*In this program we will connect to the database Test and
 * test how to execute JDBC statements.
*/
import java.sql.*;
import java.util.Scanner;

public class Connect {

	public static void main(String[] args) {
		String username;
		String password;
		Scanner input = new Scanner(System.in);
		
		//We will create a user account to access the database test
		System.out.println("Please create an account.");
		System.out.print("Username: ");
		username = input.next();
		System.out.print("Password: ");
		password = input.next();
		
		//Using the JDBC Driver we will connect to the MySQL database
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (SQLException | ClassNotFoundException e) {
			
			System.out.println(e);
		}
		
		try {
			Connection connection = DriverManager.getConnection
					("jdbc:mysql://localhost/test", username, password );
		} catch (SQLException e) {
			
			System.out.println(e);
		}
		
		//This part is not finished
		//Make a statement and carry out statement to make a new user
		Statement statement = connection.createStatement();
		statement.executeUpdate("create user '"
				+ username + "'@'%' identified by '" + password + "';");
		System.out.println("Account created");
		
		
		//ResultSet resultSet = statement.executeQuery();

		connection.close();

		
	}
	
	

}
