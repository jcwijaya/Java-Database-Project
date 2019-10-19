import java.sql.*;
import java.util.Scanner;

public class Employee {
	private int employeeId;		//Unique employee id, also username
	private String password; 	//login password for employee's account
	private String firstName;	//First name of employee
	private String lastName;	//Last name of employee
	private String phoneNumber;	
	private String email;
	
//******DATABASE INSERT METHODS*****
//Methods to insert new records into table
	
//******DATABASE SET METHODS******
//Methods to update the table
	
//******DATABASE GET METHODS******
//Methods to display and return all or parts of the table
	
//******DATABASE BOOLEAN HAS METHODS******
//Methods that show whether something can be found in the table
	
//******DATABASE CONNECT METHOD******
	//This method creates a connection to the database
		//and returns a Connection object
	public static Connection connect() {
		try {										 
			Class.forName("com.mysql.jdbc.Driver");   
			return DriverManager.getConnection("jdbc:mysql://localhost/test?autoReconnect=true&useSSL=false", "customer1", "#mtsu" );
			}
		catch(SQLException | ClassNotFoundException exception) {
			System.out.println(exception);
			return null;
			}
		}
	
	
}
