package WebMart;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MySql {
	private static String driver;
	private static String url;
	private static String dbUser;
	private static String dbPassword;
	
	public static void setDriver(String aDriver) {
		driver = aDriver;
	}
	
	public static void setUrl(String aUrl) {
		url = aUrl;
	}
	
	public static void setDbUser(String user) {
		dbUser = user;
	}
	
	public static void setDbPassword(String pass) {
		dbPassword = pass;
	}
	
	public static String getDriver() {
		return driver;
	}
	
	public static String getUrl() {
		return url;
	}
	
	public static String getDbUser() {
		return dbUser;
	}
	
	public static String getDbPassword() {
		return dbPassword;
	}
	
	//This method reads the database login information from
	//a file and sets the values for MySql class
	public static void readInfoFromFile() {
		try {
			File file = new File("mysql_info");
			Scanner scanner = new Scanner(file);
			scanner.useDelimiter("\\n");
			setDriver(scanner.next().trim());
			setUrl(scanner.next().trim());
			setDbUser(scanner.next().trim());
			setDbPassword(scanner.next().trim());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//This method will write database info into a text file to save it
	//It will write the private data fields
	public static void writeInfoToFile() {
		
	}
	
	public static Connection connect() {
		try {										 
			Class.forName(driver); 
			return DriverManager.getConnection(url, dbUser, dbPassword );
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
