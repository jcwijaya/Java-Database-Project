package Resource;
/*
 * Jessica Wijaya
 * Course: CSCI 3033
 * Assignment: WebMart database management system
 * This file contains the Employee class 
 */


import java.sql.*;
import java.util.Scanner;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Random;
import java.util.ArrayList;

//This file contains the Customer class
public class Customer {
	private SimpleIntegerProperty customerId;		//Unique customer id
	private SimpleStringProperty firstName;	//First name of customer
	private SimpleStringProperty lastName;	//Last name of customer
	private SimpleStringProperty phoneNumber;	
	private SimpleStringProperty email;
	
//******CONSTRUCTORS******
	Customer(){
		customerId = new SimpleIntegerProperty(0);
		firstName = new SimpleStringProperty("");
		lastName = new SimpleStringProperty("");
		phoneNumber = new SimpleStringProperty("");
		email = new SimpleStringProperty("");
	}
	Customer(int id, String first, String last, String number, String emailAddress){
		customerId = new SimpleIntegerProperty(id);
		firstName = new SimpleStringProperty(first);
		lastName = new SimpleStringProperty(last);
		phoneNumber = new SimpleStringProperty(number);
		email = new SimpleStringProperty(emailAddress);
	}
	
//******INSTANCE METHODS******
	public void setCustomerId(int id) {
		customerId = new SimpleIntegerProperty(id);
	}
	
	public int getCustomerId() {
		return customerId.get();
	}
	
	public void setFirstName(String first) {
		firstName = new SimpleStringProperty(first);
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public void setLastName(String last) {
		lastName = new SimpleStringProperty(last);
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public void setPhoneNumber(String number) {
		phoneNumber = new SimpleStringProperty(number);
	}
	
	public String getPhoneNumber() {
		return phoneNumber.get();
	}
	
	public void setEmail(String emailAddress) {
		email = new SimpleStringProperty(emailAddress);
	}
	
	public String getEmail() {
		return email.get();
	}
	
//******MISC METHODS******
	//This method asks the user for their info and assigns
	//the info to data fields. Then it inserts into database
	public void inputCustomerInfo(Connection conn) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter your name.");
		System.out.print("First name: ");
		firstName = new SimpleStringProperty(input.next());
		System.out.print("Last name: ");
		lastName = new SimpleStringProperty(input.next());
		System.out.print("Phone number: ");
		phoneNumber = new SimpleStringProperty(input.next());
		System.out.print("Email: ");
		email = new SimpleStringProperty(input.next());
		customerId = new SimpleIntegerProperty(createId());
		saveData(conn);
		
		input.close();
	}
	
	//This method inserts a new record into Customer table
	private void saveData(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers " +
			"(customerId, firstName, lastName, phoneNumber, email) VALUES(?, ?, ?, ?, ?);");
			stmt.setInt(1, customerId.get());
			stmt.setString(2, firstName.get());
			stmt.setString(3, lastName.get());
			stmt.setString(4, phoneNumber.get());
			stmt.setString(5, email.get()); 
				
			stmt.executeUpdate();
			}
		catch(SQLException exception) {
			System.out.println(exception);
		}
	}
	
	//This method reads customer into from a
	//text file and inserts it into the database
	//Pre-condition: Must check for duplicate entries
	//Maybe create a method to check database for duplicates in future
	public void readCustomerInfo() {
		
	}
	
	//This method returns a unique id number for each new customer
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
				id = (int)(rand.nextDouble() * 100000 + 100000);
				ResultSet result = statement.executeQuery("SELECT * FROM customers");
					
				//Use loop to compare id with ones in database
				while(result.next()) {
					if(id == result.getInt("customerId")) {
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
	/*These methods find the customer record by given customerId
	 * and update the data fields if the record is found 
	 * Need to add input validation!
	 */
	
	//Inserts the data fields of this object into database
	public void insert() {
		try {
			Connection conn = connect();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers " +
			"(customerId, firstName, lastName, phoneNumber, email) VALUES(?, ?, ?, ?, ?);");
			stmt.setInt(1, customerId.get());
			stmt.setString(2, firstName.get());
  			stmt.setString(3, lastName.get());
			stmt.setString(4, phoneNumber.get());
			stmt.setString(5, email.get()); 
				
			stmt.executeUpdate();
			}
		catch(SQLException exception) {
			System.out.println(exception);
		}
	}
	
	public static void updateCustomerId(int oldId, int newId) {
		try {
			Connection conn = connect();
			//Search for customer record, if successful, update it
			if(hasCustomerId(oldId) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET customerId = ? WHERE customerId = ?");
				stmt.setInt(1, newId);
				stmt.setInt(2, oldId);
				
				stmt.executeUpdate();
			}
			else {
				System.out.println("Customer not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updateFirstName(int id, String first) {
		try {
			Connection conn = connect();
			//Search for customer record, if successful, update it
			if(hasCustomerId(id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET firstName = ? WHERE customerId = ?");
				stmt.setString(1, first);
				stmt.setInt(2, id);
				stmt.executeUpdate();
			}
			else {
				System.out.println("Customer not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updateLastName(int id, String last) {
		try {
			Connection conn = connect();
			//Search for customer record, if successful, update it
			if(hasCustomerId(id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET lastName = ? WHERE customerId = ?");
				stmt.setString(1, last);
				stmt.setInt(2, id);
				stmt.executeUpdate();
			}
			else {
				System.out.println("Customer not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updatePhoneNumber(int id, String number) {
		try {
			Connection conn = connect();
			//Search for customer record, if successful, update it
			if(hasCustomerId(id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET phoneNumber = ? WHERE customerId = ?");
				stmt.setString(1, number);
				stmt.setInt(2, id);
				stmt.executeUpdate();
			}
			else {
				System.out.println("Customer not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updateEmail(int id, String emailAddress) {
		try {
			Connection conn = connect();
			//Search for customer record, if successful, update it
			if(hasCustomerId(id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET email = ? WHERE customerId = ?");
				stmt.setString(1, emailAddress);
				stmt.setInt(2, id);
				stmt.executeUpdate();
			}
			else {
				System.out.println("Customer not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
//******DATABASE BOOLEAN HAS METHODS******
	//These methods check whether the given data field is found in the customers table
	//and return a boolean value
	
	public static boolean hasCustomerId(int id) {
		try {
			Connection conn = connect();
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers WHERE customerId = ?");
			preparedStmt.setInt(1, id);
			
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
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers WHERE firstName = ? AND lastName = ?");
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
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where phoneNumber = ?");
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
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where email = ?");
			preparedStmt.setString(1, emailAddress);
			
			ResultSet result = preparedStmt.executeQuery();
			return result.next();
		}
		catch(SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
//*****DATABASE SEARCH METHODS*******
	//This method searches the database for a customer with
	//the passed in customerId and returns Customer object
	public static ArrayList<Customer> searchCustomerId(int id) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where customerId = ?");
		preparedStmt.setInt(1, id);
		
		ResultSet result = preparedStmt.executeQuery();
		
		//customerId should be unique and only have one record in ResultSet
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Customer(result.getInt("customerId"), result.getString("firstName"), result.getString("lastName"),
					result.getString("phoneNumber"), result.getString("email")));
		}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	//This method searches for customers with a first and last name
	//matching the passed in parameters.
	public static ArrayList<Customer> searchFirstName(String first) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where firstName = ?");
		preparedStmt.setString(1, first);
		
		ResultSet result = preparedStmt.executeQuery();
		
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Customer(result.getInt("customerId"), result.getString("firstName"), result.getString("lastName"),
					result.getString("phoneNumber"), result.getString("email")));
		}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static ArrayList<Customer> searchLastName(String last) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where lastName = ?");
		preparedStmt.setString(1, last);
		
		ResultSet result = preparedStmt.executeQuery();
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Customer(result.getInt("customerId"), result.getString("firstName"), result.getString("lastName"),
					result.getString("phoneNumber"), result.getString("email")));
		}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	//This method searches for customers with a phone number
	//matching the passed in number
	public static ArrayList<Customer> searchPhoneNumber(String number) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where phoneNumber = ?");
		preparedStmt.setString(1, number);
		
		ResultSet result = preparedStmt.executeQuery();
		
		//Use loop to go through ResultSet
				while(result.next()){
					//Create and insert object into list
					list.add(new Customer(result.getInt("customerId"), result.getString("firstName"), result.getString("lastName"),
							result.getString("phoneNumber"), result.getString("email")));
				}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	//This method searches for customers with an email
	//matching the passed in emailAdress
	public static ArrayList<Customer> searchEmail(String emailAddress) {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where email = ?");
		preparedStmt.setString(1, emailAddress);
		
		ResultSet result = preparedStmt.executeQuery();
		
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Customer(result.getInt("customerId"), result.getString("firstName"), result.getString("lastName"),
					result.getString("phoneNumber"), result.getString("email")));
		}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
//******DATABASE GET TABLE METHODS******
	//This returns a list of Customer objects in
	//ascending order by first name, then by last name
	public static ArrayList<Customer> getTableAscFirstName() {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			Connection conn = connect();
			Statement statement = conn.createStatement();
			
			boolean hasResult = statement.execute("SELECT * FROM customers ORDER BY firstName, lastName ASC");
			
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
				
				//Use loop to go through ResultSet rows
				while(result.next()){
					//Create and insert object into list
					list.add(new Customer(result.getInt("customerId"), result.getString("firstName"), result.getString("lastName"),
							result.getString("phoneNumber"), result.getString("email")));
				}
				
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	//This method returns list of Customer table 
	//by ascending last name, then by first name
	public static ArrayList<Customer> getTableAscLastName() {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			Connection conn = connect();
			Statement statement = conn.createStatement();
			
			boolean hasResult = statement.execute("SELECT * FROM customers ORDER BY lastName, firstName ASC");
			
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
				
				//Use loop to go through ResultSet rows
				while(result.next()){
					//Create and insert object into list
					list.add(new Customer(result.getInt("customerId"), result.getString("firstName"), result.getString("lastName"),
							result.getString("phoneNumber"), result.getString("email")));
				}
				
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
		return list;
	}
	
	//This method returns list of Customer obj in order of entry
	public static ArrayList<Customer> getTable() {
		ArrayList<Customer> list = new ArrayList<Customer>();
		try {
			Connection conn = connect();
			Statement statement = conn.createStatement();
			
			boolean hasResult = statement.execute("SELECT * FROM customers");
			
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
				
				//Use loop to go through ResultSet rows
				while(result.next()){
					//Create and insert object into list
					list.add(new Customer(result.getInt("customerId"), result.getString("firstName"), result.getString("lastName"),
							result.getString("phoneNumber"), result.getString("email")));
				}
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		
		return list;
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
	
	/* Syntax for ResultSetMetaData
	 * ResultSetMetaData meta = result.getMetaData();
		//Find number of columns in table
		int columnCount = meta.getColumnCount();
		
		//use loop to print column names
		for(int i = 1; i <= columnCount; i++) {
			//print out column names (later can give labels)
			System.out.printf("%-15s", meta.getColumnLabel(i));
		}
		System.out.println();
		
		//print out the table
		while(result.next()) {
			System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
					result.getInt("customerId"), result.getString("firstName"),
					result.getString("lastName"), result.getString("phoneNumber"),
					result.getString("email"));
		}
	*/
}

