
import java.sql.*;
import java.util.Scanner;

//This file contains the Customer class
public class Customer {
	private int customerId;		//Unique customer id
	private String firstName;	//First name of customer
	private String lastName;	//Last name of customer
	private String phoneNumber;	
	private String email;
	
//******DATABASE INSERT METHODS******
	//This method asks the user for their info and assigns
	//the info to data fields. Then it inserts into database
	public void inputCustomerInfo(Connection conn) {
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
		saveData(conn);
		
		input.close();
	}
	
	//This method reads customer into from a
	//text file and inserts it into the database
	//Pre-condition: Must check for duplicate entries
	//Maybe create a method to check database for duplicates in future
	public void readCustomerInfo() {
		
	}
	
	//This method returns a unique id number for each new customer
	public int createId() {
		//Search database user table for already taken id numbers
		
		int id=0;
		
		return id; 
	}
	
//******DATABASE SET METHODS******
	/*These methods find the customer record by given customerId
	 * and update the data fields if the record is found 
	 * Need to add input validation!
	 */
	
	public static void setCustomerId(Connection conn, int oldId, int newId) {
		try {
			//Search for customer record, if successful, update it
			if(hasCustomerId(conn, oldId) == true) {
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
	
	public static void setName(Connection conn, int id, String first, String last) {
		try {
			//Search for customer record, if successful, update it
			if(hasCustomerId(conn, id) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE customers SET firstName = ?, lastName = ? WHERE customerId = ?");
				stmt.setString(1, first);
				stmt.setString(2, last);
				stmt.setInt(3, id);
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
	
	public static void setPhoneNumber(Connection conn, int id, String number) {
		try {
			//Search for customer record, if successful, update it
			if(hasCustomerId(conn, id) == true) {
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
	
	public static void setEmail(Connection conn, int id, String emailAddress) {
		try {
			//Search for customer record, if successful, update it
			if(hasCustomerId(conn, id) == true) {
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
	
	public static boolean hasCustomerId(Connection conn, int id) {
		try {
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where customerId = ?");
			preparedStmt.setInt(1, id);
			
			ResultSet result = preparedStmt.executeQuery();
			
			return result.next();
			
			}
			catch(SQLException e) {
				System.out.println(e);
				return false;
			}
	}
	
	public static boolean hasName(Connection conn, String first, String last) {
		try {
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where firstName = ? and lastName = ?");
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
	
	public static boolean hasPhoneNumber(Connection conn, String number) {
		try {
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
	
	public static boolean hasEmail(Connection conn, String emailAddress) {
		try {
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
	//the passed in customerId
	public static void searchCustomerId(Connection conn, int id) {
		int count = 0;
		
		try {
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where customerId = ?");
		preparedStmt.setInt(1, id);
		
		ResultSet result = preparedStmt.executeQuery();
		//customerId should be unique and only print one record
		while(result.next()) {
			System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
					result.getInt("customerId"), result.getString("firstName"),
					result.getString("lastName"), result.getString("phoneNumber"),
					result.getString("email"));
			count++;
			}
		
		//if the ResultSet is empty, print message
		if(count == 0) {
			System.out.println("Customer not found");
		}
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	//This method searches for customers with a first and last name
	//matching the passed in parameters.
	public static void searchName(Connection conn, String first, String last) {
		int count = 0;
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where firstName = ? and lastName = ?");
			preparedStmt.setString(1, first);
			preparedStmt.setString(2, last);
			
			ResultSet result = preparedStmt.executeQuery();
			while(result.next()) {
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
						result.getInt("customerId"), result.getString("firstName"),
						result.getString("lastName"), result.getString("phoneNumber"),
						result.getString("email"));
				count ++;
			}
			
			//if the ResultSet is empty, print message
			if(count == 0) {
				System.out.println("Customer not found.");
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	//This method searches for customers with a phone number
	//matching the passed in number
	public static void searchPhoneNumber(Connection conn, String number) {
		int count = 0;
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where phoneNumber = ?");
			preparedStmt.setString(1, number);
			
			ResultSet result = preparedStmt.executeQuery();
			while(result.next()) {
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
						result.getInt("customerId"), result.getString("firstName"),
						result.getString("lastName"), result.getString("phoneNumber"),
						result.getString("email"));
				count ++;
			}
			
			//if the ResultSet is empty, print message
			if(count == 0) {
				System.out.println("Customer not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	//This method searches for customers with an email
	//matching the passed in emailAdress
	public static void searchEmail(Connection conn, String emailAddress) {
		int count = 0;
		
		try {
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM customers where email = ?");
			preparedStmt.setString(1, emailAddress);
			
			ResultSet result = preparedStmt.executeQuery();
			while(result.next()) {
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
						result.getInt("customerId"), result.getString("firstName"),
						result.getString("lastName"), result.getString("phoneNumber"),
						result.getString("email"));
				count ++;
			}
			
			//if the ResultSet is empty, print message
			if(count == 0) {
				System.out.println("Customer not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
//******DATABASE GET METHODS******
	//This method displays the entire customers table in
	//ascending order by first name, then by last name
	public static void getTableAscFirstName(Connection conn) {
		try {
			Statement statement = conn.createStatement();
			
			boolean hasResult = statement.execute("SELECT * FROM customers ORDER BY firstName, lastName ASC");
			
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
				ResultSetMetaData meta = result.getMetaData();
				
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
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	//This method displays the entire customers table 
	//by ascending last name, then by first name
	public static void getTableAscLastName(Connection conn) {
		try {
			Statement statement = conn.createStatement();
			
			boolean hasResult = statement.execute("SELECT * FROM customers ORDER BY lastName, firstName ASC");
			
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
				ResultSetMetaData meta = result.getMetaData();
				
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
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	//This method prints out entire customers table in order of entry
	//Takes in a Connection
	public static void getTable(Connection conn) {
		try {
			Statement statement = conn.createStatement();
			
			boolean hasResult = statement.execute("SELECT * FROM customers");
			
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
				ResultSetMetaData meta = result.getMetaData();
				
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
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	/*
	public static int getCustomerId(Connection conn) {
		
	}
	
	public static String getName() {
		
	}
	
	public static String getPhoneNumber() {
		
	}
	
	public static String getEmail() {
		
	}
	*/
	
//******DATABASE CONNECT & SAVE METHODS******
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
	
	//This method inserts firstName and lastName into user table
	private void saveData(Connection conn) {
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO customers " +
			"(customerId, firstName, lastName, phoneNumber, email) VALUES(?, ?, ?, ?, ?);");
			stmt.setInt(1, customerId);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, phoneNumber);
			stmt.setString(5, email); 
			
			stmt.executeUpdate();
		}
		catch(SQLException exception) {
			System.out.println(exception);
		}
	}
		

	

}

