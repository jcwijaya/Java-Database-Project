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
		employeeId = createId(conn);
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
		
	//This method returns a unique id number for each new employee
	public static int createId(Connection conn) {
		//Randomly generate a 6 digit id
			Random rand = new Random(System.currentTimeMillis());
			int id = (int)(rand.nextDouble() * 100000 + 100000);
			boolean match = true;
				
			try {
				//Compare the id with the ones in database to ensure uniqueness
				Statement statement = conn.createStatement();
					
				//Use loop to keep generating id's until you get a unique one
				while(match) {
					match = false;
					id = (int)(rand.nextDouble() * 100000 + 100000);
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

//******DATABASE DELETE METHOD******
	public void delete(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM employees WHERE employeeId = ?");
			
			stmt.setInt(1, employeeId);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
//******DATABASE UPDATE & INSERT METHODS******
	//Inserts the data fields of this object into database
	public void insert(Connection conn) {
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
	
	public static void updateExistingEmployee(int id, String pass, String first, String last,
			String phone, String emailAddress, Connection conn) {
		try {
			//Search for customer record, if successful, update it
			if(hasEmployeeId(id, conn) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE employees SET password = ?, " +
						"firstName = ?, lastName = ?, phoneNumber = ?, email = ? WHERE employeeId = ?");
				stmt.setString(1,  pass);
				stmt.setString(2, first);
				stmt.setString(3, last);
				stmt.setString(4, phone);
				stmt.setString(5, emailAddress);
				stmt.setInt(6, id);
				
				stmt.executeUpdate();	
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
//Methods to update the table
	public static void updateEmployeeId(int oldId, int newId, Connection conn) {
		try {
			//Search for employee record, if successful, update it
			if(hasEmployeeId(oldId, conn) == true) {
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
	
	public static void updatePassword(int id, String newPass, Connection conn) {
		try {
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id, conn) == true) {
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
	
	public static void updateFirstName(int id, String first, Connection conn) {
		try {
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id, conn) == true) {
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
	
	public static void updateLastName(int id, String last, Connection conn) {
		try {
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id, conn) == true) {
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
	
	public static void updatePhoneNumber(int id, String number, Connection conn) {
		try {
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id, conn) == true) {
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
	
	public static void updateEmail(int id, String emailAddress, Connection conn) {
		try {
			//Search for employee record, if successful, update it
			if(hasEmployeeId(id, conn) == true) {
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
	public static ArrayList<Employee> getTableAscFirstName(Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
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
	public static ArrayList<Employee> getTableAscLastName(Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
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
	public static ArrayList<Employee> getTable(Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
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
	public static ArrayList<Employee> searchEmployeeId(int id, Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
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
	public static ArrayList<Employee> searchFirstName(String first, Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
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
	
	public static ArrayList<Employee> searchLastName(String last, Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
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
	public static ArrayList<Employee> searchPhoneNumber(String number, Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
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
	public static ArrayList<Employee> searchEmail(String emailAddress, Connection conn) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
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
	public static boolean hasEmployeeId(int id, Connection conn) {
		try {
			//Connection conn = connect();
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
	
	public static boolean hasLogin(int id, String pass, Connection conn) {
		try {
			//Connection conn = connect();
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
	
	public static boolean hasName(String first, String last, Connection conn) {
		try {
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
	
	public static boolean hasPhoneNumber(String number, Connection conn) {
		try {
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
	
	public static boolean hasEmail(String emailAddress, Connection conn) {
		try {
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
	
}
