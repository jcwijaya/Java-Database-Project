package WebMart;
/*
 * Jessica Wijaya
 * Course: CSCI 3033
 * Assignment: WebMart database management system
 * This file contains the Inventory class 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Inventory {
	private long productCode;
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
	
	public Inventory(Long code, String aCategory, String name, Double aPrice, int aStock) {
		productCode = code;
		category = aCategory;
		productName = name;
		price = aPrice;
		stock = aStock;
	}	
	
//******INSTANCE METHODS******
	public long getProductCode() {
		return productCode;
	}
	
	public void setProductCode(long code) {
		productCode = code;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String aCategory) {
		category = aCategory;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String name) {
		productName = name;
	}
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double aPrice) {
		price = aPrice;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int newStock) {
		stock = newStock;
	}
	
//******MISC METHODS******
	public static ArrayList<Inventory> ReadFromFile() throws FileNotFoundException {
		File file = new File("items");
		Scanner scanner = new Scanner(file);
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		
		String strProductCode;
		String category;
		String productName;
		String strPrice;
		String strStock;
		
		double price;
		int stock;
		long productCode;
		
		while(scanner.hasNextLine())
		{
			scanner.useDelimiter(";");
			
			strProductCode = scanner.next();
			productCode = Long.parseLong(strProductCode.trim());
			
			category = scanner.next();
			
			productName = scanner.next();
			
			strPrice = scanner.next();
			price = Double.parseDouble(strPrice.trim());
			
			strStock = scanner.next();
			stock = Integer.parseInt(strStock.trim());
			
			
			list.add(new Inventory(productCode, category.trim(), productName.trim(), price, stock));
		}
		
		scanner.close();
		
		return list;
	}
	
	
	//This method returns a unique id number for each new Inventory item
	public static long createId() {
		//Randomly generate a 6 digit id
			Random rand = new Random(System.currentTimeMillis());
			long id = (long)(rand.nextDouble() * 10000000 + 10000000);
			boolean match = true;
					
			try {
				Connection conn = connect();
				//Compare the id with the ones in database to ensure uniqueness
				Statement statement = conn.createStatement();
						
				//Use loop to keep generating id's until you get a unique one
				while(match) {
					match = false;
					id = (long)(rand.nextDouble() * 10000000 + 10000000);
					ResultSet result = statement.executeQuery("SELECT * FROM inventory");
								
					//Use loop to compare id with ones in database
					while(result.next()) {
						if(id == result.getLong("productCode")) {
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
	public void delete() {
		try {
			Connection conn = connect();
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM inventory WHERE productCode = ?");
			
			stmt.setLong(1, productCode);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//******DATABASE UPDATE & INSERT METHODS*****
//Inserts the data fields of this object into database
	public void insert() {
		try {
			Connection conn = connect();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO inventory " +
			"(productCode, category, productName, price, stock) VALUES(?, ?, ?, ?, ?);");
			stmt.setLong(1, productCode);
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
	public static void updateExistingItem(Long code, String category, String name, double price, int stock) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(code) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET " + 
						"category = ?, productName = ?, price = ?, stock = ? WHERE productCode = ?");
				stmt.setString(1, category);
				stmt.setString(2, name);
				stmt.setDouble(3, price);
				stmt.setInt(4, stock);
				stmt.setLong(5, code);
				
				stmt.executeUpdate();
			}
			else {
				System.out.println("Item not found.");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateProductCode(Long oldCode, Long newCode) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(oldCode) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET productCode = ? WHERE productCode = ?");
				stmt.setLong(1, newCode);
				stmt.setLong(2, oldCode);
					
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
	public static void updateCategory(Long code, String newCat) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(code) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET category = ? WHERE productCode = ?");
				stmt.setString(1, newCat);
				stmt.setLong(2, code);
				
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
	public static void updateProductName(Long code, String newName) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(code) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET productName = ? WHERE productCode = ?");
				stmt.setString(1, newName);
				stmt.setLong(2, code);
				
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
	
	public static void updatePrice(Long code, Double newPrice) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(code) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET price = ? WHERE productCode = ?");
				stmt.setDouble(1, newPrice);
				stmt.setLong(2, code);
				
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
	public static void updateStock(Long code, int newStock) {
		try {
			Connection conn = connect();
			//Search for Inventory record, if successful, update it
			if(hasProductCode(code) == true) {
				PreparedStatement stmt = conn.prepareStatement("UPDATE inventory SET stock = ? WHERE productCode = ?");
				stmt.setInt(1, newStock);
				stmt.setLong(2, code);
				
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
	
	//This returns a list of Inventory objects in
	//ascending order by category, then by productName
	public static ArrayList<Inventory> getTableAscCategory() {
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		try {
			Connection conn = connect();
			Statement statement = conn.createStatement();
				
			boolean hasResult = statement.execute("SELECT * FROM inventory ORDER BY category, productName ASC");
				
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
					
				//Use loop to go through ResultSet rows
				while(result.next()){
					//Create and insert object into list
					list.add(new Inventory(result.getLong("productCode"), result.getString("category"), result.getString("productName"), 
							result.getDouble("price"), result.getInt("stock")));
				}
				
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
			
		return list;
	}
	
	//This method returns list of Inventory objects in order of entry
	public static ArrayList<Inventory> getTable() {
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		try {
			Connection conn = connect();
			Statement statement = conn.createStatement();
				
			boolean hasResult = statement.execute("SELECT * FROM inventory");
				
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
					
				//Use loop to go through ResultSet rows
				while(result.next()){
					//Create and insert object into list
					list.add(new Inventory(result.getLong("productCode"), result.getString("category"), result.getString("productName"), 
							result.getDouble("price"), result.getInt("stock")));
				}
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
			
		return list;
	}
	
	//This method returns list of Inventory objects in order of productCode
	public static ArrayList<Inventory> getTableAscProductCode() {
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		try {
			Connection conn = connect();
			Statement statement = conn.createStatement();
					
			boolean hasResult = statement.execute("SELECT * FROM inventory ORDER BY productCode ASC");
					
			if(hasResult == true) {
				ResultSet result = statement.getResultSet();
						
				//Use loop to go through ResultSet rows
				while(result.next()){
					//Create and insert object into list
					list.add(new Inventory(result.getLong("productCode"), result.getString("category"), result.getString("productName"), 
							result.getDouble("price"), result.getInt("stock")));
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
	// and returns ArrayList of Inventory objects that match the parameter
	public static ArrayList<Inventory> searchProductCode(Long code) {
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM inventory where productCode = ?");
		preparedStmt.setLong(1, code);
			
		ResultSet result = preparedStmt.executeQuery();
			
		//productCode should be unique and only have one record in ResultSet
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Inventory(result.getLong("productCode"), result.getString("category"), result.getString("productName"), 
					result.getDouble("price"), result.getInt("stock")));
		}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static ArrayList<Inventory> searchCategory(String aCat) {
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM inventory where category = ?");
		preparedStmt.setString(1, aCat);
			
		ResultSet result = preparedStmt.executeQuery();
			
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Inventory(result.getLong("productCode"), result.getString("category"), result.getString("productName"), 
					result.getDouble("price"), result.getInt("stock")));
		}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static ArrayList<Inventory> searchProductName(String name) {
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM inventory where productName = ?");
		preparedStmt.setString(1, name);
			
		ResultSet result = preparedStmt.executeQuery();
			
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Inventory(result.getLong("productCode"), result.getString("category"), result.getString("productName"), 
					result.getDouble("price"), result.getInt("stock")));
		}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
//Searches for specific price and sorts by category in alphabetical order
	public static ArrayList<Inventory> searchPrice(int aPrice) {
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM inventory where price = ? ORDER BY category ASC");
		preparedStmt.setDouble(1, aPrice);
			
		ResultSet result = preparedStmt.executeQuery();
			
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Inventory(result.getLong("productCode"), result.getString("category"), result.getString("productName"), 
					result.getDouble("price"), result.getInt("stock")));
		}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
//This returns items in a range of prices with secondary ordering by price then by category
	public static ArrayList<Inventory> searchPriceRange(double low, double high) {
		ArrayList<Inventory> list = new ArrayList<Inventory>();
		try {
		Connection conn = connect();
		PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM inventory WHERE price BETWEEN ? AND ? ORDER BY price, category ASC");
		preparedStmt.setDouble(1, low);
		preparedStmt.setDouble(2, high);
			
		ResultSet result = preparedStmt.executeQuery();
			
		//Use loop to go through ResultSet
		while(result.next()){
			//Create and insert object into list
			list.add(new Inventory(result.getLong("productCode"), result.getString("category"), result.getString("productName"), 
					result.getDouble("price"), result.getInt("stock")));
		}
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}
//******DATABASE BOOLEAN HAS METHODS******
//Methods that show whether something can be found in the table
	public static boolean hasProductCode(long code) {
		try {
			Connection conn = connect();
			PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM inventory where productCode = ?");
			preparedStmt.setLong(1, code);
				
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
