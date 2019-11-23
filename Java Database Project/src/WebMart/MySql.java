package WebMart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//This method will write database info into a text file to save it
	//It will write the private data fields
	public static void writeInfoToFile(String aDriver, String aUrl, String user, String pass) {
		try {
			FileWriter writer = new FileWriter("mysql_info");
			writer.write(aDriver + "\n");
			writer.write(aUrl + "\n");
			writer.write(user + "\n");
			writer.write(pass + "\n");
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection connect() {
		try {										 
			Class.forName(driver); 
			return DriverManager.getConnection(url, dbUser, dbPassword );
		}
		catch(SQLException | ClassNotFoundException e) {
			return null;
		}
	}
}
