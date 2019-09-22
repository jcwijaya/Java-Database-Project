
import java.sql.*;
import java.util.Scanner;

//This file contains the User class
public class User {
	private int customerId;		//Unique customer id
	private String firstName;	//First name of customer
	private String lastName;	//Last name of customer
	private String phoneNumber;	
	private String email;
	
	//This method asks the user for their name and assigns
	//the strings to firstName and lastName
	public void inputUserInfo() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your name.");
		System.out.print("First name: ");
		firstName = input.next();
		System.out.print("Last name: ");
		lastName = input.next();
		System.out.print("Phone number: ");
		phoneNumber = input.next();
		System.out.print("Email: ");
		email = input.next();
		customerId = createId();
		insertData();
		
		input.close();
		System.out.println("Program end");
	}
	
	//This method returns a unique id number for each new customer
	public int createId() {
		//Search database user table for already taken id numbers
		
		int id=0;
		
		return id;
	}
	
	//This method creates a connection to the database
	//and returns a Connection object
	private Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false", "root", "#mtsujava2" );
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
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers " +
			"(customerId, firstName, lastName, phoneNumber, email) VALUES(?, ?, ?, ?, ?);");
			stmt.setInt(1, customerId);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, phoneNumber);
			stmt.setString(5, email);
			
			stmt.executeUpdate();
			System.out.println("Data stored successfully");
			conn.close();
		}
		catch(SQLException exception) {
			System.out.println(exception);
		}
	}
		

	

}

