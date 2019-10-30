package WebMart;
/*
 * Jessica Wijaya
 * Course: CSCI 3033
 * Assignment: WebMart database management system
 * This file contains the Employee class 
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Employee {
	private int employeeId;		//Unique employee id, also username
	private String password; 	//login password for employee's account
	private String firstName;	//First name of employee
	private String lastName;	//Last name of employee
	private String phoneNumber;	
	private String email;
	
//******CONSTRUCTORS******
	Employee(){
		employeeId = 0;
		password = "";
		firstName = "";
		lastName = "";
		phoneNumber = "";
		email = "";
	}
	
	Employee(int id, String pass, String first, String last, String number, String emailAddress){
		employeeId = id;
		password = pass;
		firstName = first;
		lastName = last;
		phoneNumber = number;
		email = emailAddress;
	}

//******INSTANCE METHODS******
	public void setEmployeeId(int id) {
		employeeId = id;
	}
		
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setPassword(String pass) {
		password = pass;
	}
	
	public String getPassword() {
		return password;
	}
		
	public void setFirstName(String first) {
		firstName = first;
	}
		
	public String getFirstName() {
		return firstName;
	}
		
	public void setLastName(String last) {
		lastName = last;
	}
		
	public String getLastName() {
		return lastName;
	}
		
	public void setPhoneNumber(String number) {
		phoneNumber = number;
	}
		
	public String getPhoneNumber() {
		return phoneNumber;
	}
		
	public void setEmail(String emailAddress) {
		email = emailAddress;
	}
		
	public String getEmail() {
		return email;
	}
			
//******MISC METHODS*****
//Methods to insert new records into table
	
	//This method asks the user for their info and assigns
		//the info to data fields. Then it inserts into database
	public void inputEmployeeInfo(Connection conn) {
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
		employeeId = createId();
		System.out.print("Password: ");
		password = input.next();
		saveData(conn);
		
		input.close();
		}
	
	//This method inserts a new record into Employee table
	private void saveData(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO employees " +
			"(employeeId, password, firstName, lastName, phoneNumber, email) VALUES(?, ?, ?, ?, ?, ?);");
			stmt.setInt(1, employeeId);
			stmt.setString(2, password);
			stmt.setString(3, firstName);
			stmt.setString(4, lastName);
			stmt.setString(5, phoneNumber);
			stmt.setString(6, email); 
					
			stmt.executeUpdate();
			}
		catch(SQLException exception) {
			System.out.println(exception);
			}
	}
	
	//This method reads employee info from a
	//text file and inserts it into the database
	//Pre-condition: Must check for duplicate entries
	//Maybe create a method to check database for duplicates in future
	public void readEmployeeInfo() {
			
	}
		
	//This method returns a unique id number for each new employee
	public static int createId() {
		//Randomly generate a 6 digit id
			Random rand = new Random(System.currentTimeMillis());
			int id = (int)(rand.nextDouble() * 100000 + 100000);
			boolean match = true;
				
			try {
				Connection conn = connect();
				//Compare the id with the ones in database to ensure uniqueness
				Statement statement = conn.createStatement();
					
				//Use loop to keep generating id's until you get a unique one
				while(match) {
					match = false;
					id = (int)(rand.nextDouble() * 1000000 + 100000);
					ResultSet result = statement.executeQuery("SELECT * FROM employees");
							
					//Use loop to compare id with ones in database
					while(result.next()) {
						if(id == result.getInt("employeeId")) {
							match = true;
							break;
						}
					}
				}
					
				return id;
			}
			catch(SQLException e) {
				System.out.println(e);
				return 000000;
			}
	}
	
//******DATABASE UPDATE & INSERT METHODS******
	//Inserts the data fields of this object into database
	public void insert() {
		try {
			Connection conn = connect();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO employees " +
			"(employeeId, password, firstName, lastName, phoneNumber, email) VALUES(?, ?, ?, ?, ?, ?);");
			stmt.setInt(1, employeeId);
			stmt.setString(2, password);
			stmt.setString(3, firstName);
			stmt.setString(4, lastName);
			stmt.setString(5, phoneNumber); 
			stmt.setString(6, email); 
			
			stmt.executeUpdate();
			}
		catch(SQLException exception) {
			System.out.println(exception);
		}
	}
	
//Methods to update the table
	public static void updateEmployeeId(int oldId, int newId) {
		try {
			Connection conn = connect();
			//Search for employee record, if successful, update it
			if(hasEmployeeId(oldId) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET employeeId = ? WHERE employeeId = ?");
				stmt.setInt(1, newId);
				stmt.setInt(2, oldId);
				
				stmt.executeUpdate();
			}
			else {
				System.out.println("Employee not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updatePassword(int id, String newPass) {
		try {
			Connection conn = connect();
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET password = ? WHERE employeeId = ?");
				stmt.setString(1, newPass);
				stmt.setInt(2, id);
				
				stmt.executeUpdate();
			}
			else {
				System.out.println("Employee not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updateFirstName(int id, String first) {
		try {
			Connection conn = connect();
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET firstName = ? WHERE employeeId = ?");
				stmt.setString(1, first);
				stmt.setInt(2, id);
	
				stmt.executeUpdate();
			}
			else {
				System.out.println("Employee not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updateLastName(int id, String last) {
		try {
			Connection conn = connect();
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET lastName = ? WHERE employeeId = ?");
				stmt.setString(1, last);
				stmt.setInt(2, id);
	
				stmt.executeUpdate();
			}
			else {
				System.out.println("Employee not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updatePhoneNumber(int id, String number) {
		try {
			Connection conn = connect();
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET phoneNumber = ? WHERE employeeId = ?");
				stmt.setString(1, number);
				stmt.setInt(2, id);
				stmt.executeUpdate();
			}
			else {
				System.out.println("Employee not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updateEmail(int id, String emailAddress) {
		try {
			Connection conn = connect();
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET email = ? WHERE employeeId = ?");
				stmt.setString(1, emailAddress);
				stmt.setInt(2, id);
				stmt.executeUpdate();
			}
			else {
				System.out.println("Employee not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
//******DATABASE GET TABLE METHODS******
//Methods to display and return all or parts of the table
	
	//This returns a list of Employee objects in
	//ascending order by first name, then by last name
	public static ArrayList<Employee> getTableAscFirstName() {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
			Connection conn = connect();
			Statement statement = conn.createStatement();
				
			boolean hasResult = statement.execute("SELECT * FROM employees ORDER BY firstName, lastName ASC");
				
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
					
				//Use loop to go through ResultSet rows
				while(result.next()){
					//Create and insert object into list
					list.add(new Employee(result.getInt("employeeId"), result.getString("password"), result.getString("firstName"), 
							result.getString("lastName"), result.getString("phoneNumber"), result.getString("email")));
				}
				
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
			
		return list;
	}
		
	//This method returns list of Employee table 
	//by ascending last name, then by first name
	public static ArrayList<Employee> getTableAscLastName() {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
			Connection conn = connect();
			Statement statement = conn.createStatement();
				
			boolean hasResult = statement.execute("SELECT * FROM employees ORDER BY lastName, firstName ASC");
				
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
					
				//Use loop to go through ResultSet rows
				while(result.next()){
					//Create and insert object into list
					list.add(new Employee(result.getInt("employeeId"), result.getString("password"), result.getString("firstName"), 
							result.getString("lastName"), result.getString("phoneNumber"), result.getString("email")));
				}
					
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
			
		return list;
	}
		
	//This method returns list of Employee objects in order of entry
	public static ArrayList<Employee> getTable() {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
			Connection conn = connect();
			Statement statement = conn.createStatement();
				
			boolean hasResult = statement.execute("SELECT * FROM employees");
				
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
					
				//Use loop to go through ResultSet rows
				while(result.next()){
					//Create and insert object into list
					list.add(new Employee(result.getInt("employeeId"), result.getString("password"), result.getString("firstName"), 
							result.getString("lastName"), result.getString("phoneNumber"), result.getString("email")));
				}
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
			
		return list;
	}
	
//******DATABASE SEARCH METHODS******
//These methods take in a data field as parameter
// and returns ArrayList of Employee objects that match the parameter
	public static ArrayList<Employee> searchEmployeeId(int id) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees where employeeId = ?");
		preparedStmt.setInt(1, id);
		
		ResultSet result = preparedStmt.executeQuery();
		
		//employeeId should be unique and only have one record in ResultSet
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Employee(result.getInt("employeeId"), result.getString("password"), result.getString("firstName"), 
					result.getString("lastName"), result.getString("phoneNumber"), result.getString("email")));
		}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	//This method searches for employees with a first name
	//matching the passed in parameter.
	public static ArrayList<Employee> searchFirstName(String first) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees where firstName = ?");
		preparedStmt.setString(1, first);
		
		ResultSet result = preparedStmt.executeQuery();
		
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Employee(result.getInt("employeeId"),result.getString("password"), result.getString("firstName"), 
					result.getString("lastName"), result.getString("phoneNumber"), result.getString("email")));
		}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static ArrayList<Employee> searchLastName(String last) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees where lastName = ?");
		preparedStmt.setString(1, last);
		
		ResultSet result = preparedStmt.executeQuery();
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Employee(result.getInt("employeeId"), result.getString("password"), result.getString("firstName"), 
					result.getString("lastName"), result.getString("phoneNumber"), result.getString("email")));
		}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	//This method searches for employees with a phone number
	//matching the passed in number
	public static ArrayList<Employee> searchPhoneNumber(String number) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees where phoneNumber = ?");
		preparedStmt.setString(1, number);
			
		ResultSet result = preparedStmt.executeQuery();
			
		//Use loop to go through ResultSet
				while(result.next()){
					//Create and insert object into list
					list.add(new Employee(result.getInt("customerId"), result.getString("password"), result.getString("firstName"), 
							result.getString("lastName"), result.getString("phoneNumber"), result.getString("email")));
				}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	//This method searches for employees with an email
	//matching the passed in emailAdress
	public static ArrayList<Employee> searchEmail(String emailAddress) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees where email = ?");
		preparedStmt.setString(1, emailAddress);
		
		ResultSet result = preparedStmt.executeQuery();
		
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Employee(result.getInt("employeeId"), result.getString("password"), result.getString("firstName"), 
					result.getString("lastName"), result.getString("phoneNumber"), result.getString("email")));
		}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
//******DATABASE BOOLEAN HAS METHODS******
//Methods that show whether something can be found in the table
	public static boolean hasEmployeeId(int id) {
		try {
			Connection conn = connect();
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees where employeeId = ?");
			preparedStmt.setInt(1, id);
			
			ResultSet result = preparedStmt.executeQuery();
			
			return result.next();
			
			}
			catch(SQLException e) {
				System.out.println(e);
				return false;
			}
	}
	
	public static boolean hasLogin(int id, String pass) {
		try {
			Connection conn = connect();
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees WHERE employeeId = ? AND password = ?");
			preparedStmt.setInt(1, id);
			preparedStmt.setString(2,  pass);
			
			ResultSet result = preparedStmt.executeQuery();
			
			return result.next();
			
			}
			catch(SQLException e) {
				System.out.println(e);
				return false;
			}
	}
	
	public static boolean hasName(String first, String last) {
		try {
			Connection conn = connect();
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees WHERE firstName = ? AND lastName = ?");
			preparedStmt.setString(1, first);
			preparedStmt.setString(2, last);
			
			ResultSet result = preparedStmt.executeQuery();
			return result.next();
		}
		catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static boolean hasPhoneNumber(String number) {
		try {
			Connection conn = connect();
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees where phoneNumber = ?");
			preparedStmt.setString(1, number);
			
			ResultSet result = preparedStmt.executeQuery();
			return result.next();
		}
		catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static boolean hasEmail(String emailAddress) {
		try {
			Connection conn = connect();
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM employees where email = ?");
			preparedStmt.setString(1, emailAddress);
			
			ResultSet result = preparedStmt.executeQuery();
			return result.next();
		}
		catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
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
