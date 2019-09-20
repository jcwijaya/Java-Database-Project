
import java.sql.*;
import java.util.Scanner;

public class User {
	private String firstName;
	private String lastName;
	
	public static void main(String[] args) {
		User customer = new User();
		customer.inputName();
		
		return;
	}
	
	//This method asks the user for their name and assigns
	//the strings to firstName and lastName
	public void inputName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your name.");
		System.out.print("First name: ");
		firstName = input.next();
		System.out.print("Last name: ");
		lastName = input.next();
		insertData();
		
		input.close();
		System.out.println("Program end");
	}
	
	//This method creates a connection to the database
	//and returns a Connection object
	private Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false", "root", "#mtsujava" );
		}
		catch(SQLException | ClassNotFoundException exception) {
			System.out.println(exception);
			return null;
		}
	}
	
	//This method inserts firstName and lastName into user table
	private void insertData() {
		try {
			Connection conn = connect();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(firstName, lastName) VALUES(?, ?);");
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			
			stmt.executeUpdate();
			System.out.println("Data stored successfully");
		}
		catch(SQLException exception) {
			System.out.println(exception);
		}
	}
		

	

}
