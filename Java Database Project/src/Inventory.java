/*
 * Jessica Wijaya
 * Course: CSCI 3033
 * Assignment: WebMart database management system
 * This file contains the Inventory class 
 */

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Inventory {
	private int productCode;
	private String category;
	private String productName;
	private double price;
	private int stock;
	
//******CONSTRUCTORS******
	
	public Inventory() {
		productCode = 0;
		category = "";
		productName = "";
		price = 0.0;
		stock = 0;
	}
	
	public Inventory(int code, String aCategory, String name, Double aPrice, int aStock) {
		productCode = code;
		category = aCategory;
		productName = name;
		price = aPrice;
		stock = aStock;
	}	
	
//******INSTANCE METHODS******
	
//******MISC METHODS******
	//This method returns a unique id number for each new Inventory item
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
					ResultSet result = statement.executeQuery("SELECT * FROM inventory");
								
					//Use loop to compare id with ones in database
					while(result.next()) {
						if(id == result.getInt("productCode")) {
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
	
//******DATABASE UPDATE & INSERT METHODS*****
//Inserts the data fields of this object into database
	public void insert() {
		try {
			Connection conn = connect();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory " +
			"(productCode, category, productName, price, stock) VALUES(?, ?, ?, ?, ?);");
			stmt.setInt(1, productCode);
			stmt.setString(2, category);
			stmt.setString(3, productName);
			stmt.setDouble(4, price);
			stmt.setInt(5, stock); 
			
			stmt.executeUpdate();
			}
		catch(SQLException exception) {
			System.out.println(exception);
		}
	}	

//Methods to update the table
	public static void updateProductCode(int oldCode, int newCode) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(oldCode) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET productCode = ? WHERE productCode = ?");
				stmt.setInt(1, newCode);
				stmt.setInt(2, oldCode);
					
				stmt.executeUpdate();
			}
			else {
				System.out.println("Item not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	public static void updateCategory(int code, String newCat) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(code) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET category = ? WHERE productCode = ?");
				stmt.setString(1, newCat);
				stmt.setInt(2, code);
				
				stmt.executeUpdate();
			}
			else {
				System.out.println("Item not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	public static void updateProductName(int code, String newName) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(code) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET productName = ? WHERE productCode = ?");
				stmt.setString(1, newName);
				stmt.setInt(2, code);
				
				stmt.executeUpdate();
			}
			else {
				System.out.println("Item not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void updatePrice(int code, String newPrice) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(code) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET price = ? WHERE productCode = ?");
				stmt.setString(1, newPrice);
				stmt.setInt(2, code);
				
				stmt.executeUpdate();
			}
			else {
				System.out.println("Item not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
	public static void updateStock(int code, String newStock) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(code) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET stock = ? WHERE productCode = ?");
				stmt.setString(1, newStock);
				stmt.setInt(2, code);
				
				stmt.executeUpdate();
			}
			else {
				System.out.println("Item not found.");
			}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
//******DATABASE GET TABLE METHODS******
	public static ArrayList<Inventory> getTable() {
		 ArrayList<Inventory> list = new ArrayList<Inventory>();
		 return list;
	}

//******DATABASE SEARCH METHODS******	
	

//******DATABASE BOOLEAN HAS METHODS******
//Methods that show whether something can be found in the table
	public static boolean hasProductCode(int code) {
		try {
			Connection conn = connect();
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM inventory where productCode = ?");
			preparedStmt.setInt(1, code);
				
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
