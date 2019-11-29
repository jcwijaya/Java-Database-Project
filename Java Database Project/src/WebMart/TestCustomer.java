package WebMart;
import java.io.File;
import java.io.FileNotFoundException;
/*
 * Jessica Wijaya
 * Course: CSCI 3033
 * Assignment: WebMart database management system
 * This file contains the Employee class 
 */
//import java.sql.*;
//import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Scanner;

//This program tests the classes and is for debugging purposes.
public class TestCustomer{
	
	public static void main(String[] args) throws FileNotFoundException {
		/*
		MySql.readInfoFromFile();
		System.out.println(MySql.getDriver());
		System.out.println(MySql.getUrl());
		System.out.println(MySql.getDbUser());
		System.out.println(MySql.getDbPassword());
		MySql.connect();
		System.out.print("Connection success");
		*/
		String aDriver = "a123";
		String aUrl = "bdefg";
		String user = "c5";
		String pass = "d345";
		MySql.writeInfoToFile(aDriver, aUrl, user, pass);
		System.out.print("finished writing");
		
	}
		/*
		try {
			File file = new File("items");
			Scanner scanner = new Scanner(file);
			String strProductCode;
			String category;
			String productName;
			String strPrice;
			String strStock;
			double price;
			int stock;
			Long productCode;
			while(scanner.hasNext())
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
				//System.out.printf("%-15d%-15s%-15s%-15f%-15s%d", productCode, category, productName, price, stock);
			
			/*
			Inventory item = new Inventory(productCode, category.trim(), productName.trim(), price, stock);
			item.insert();

			System.out.println("Insertion successful.");
			}
			scanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return;
	}
	*/
}
	
	//Main method
		//public static void main(String[] args) {
			//Display customer table by ascending last name and enter
			//in an ID to change email
			/*
			ArrayList<Customer> list = new ArrayList<Customer>();
			list = Customer.getTableAscLastName();
			System.out.println(list.size());
			//print table
			for(int i=0; i < list.size(); i++){
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
					list.get(i).getCustomerId(), list.get(i).getFirstName(), list.get(i).getLastName(), 
					list.get(i).getPhoneNumber(), list.get(i).getEmail());
			}
			/*
			Scanner input = new Scanner(System.in);
			System.out.print("Enter ID to change email: ");
			int oldId = input.nextInt();
			System.out.print("New email: ");
			String address = input.next();
			Customer.updateEmail(oldId, address);
			list = Customer.getTableAscLastName();
			System.out.println(list.size());
			//print table
			for(int i=0; i < list.size(); i++){
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
					list.get(i).getCustomerId(), list.get(i).getFirstName(), list.get(i).getLastName(), 
					list.get(i).getPhoneNumber(), list.get(i).getEmail());
			}
			input.close();
			*/
			
			
			//Insert new records into table
			/*
			Scanner input = new Scanner(System.in);
			
			Customer e = new Customer();
			e.setCustomerId(Customer.createId());
			System.out.print("First Name: ");
			e.setFirstName(input.next());
			System.out.print("Last Name: ");
			e.setLastName(input.next());
			System.out.print("Phone Number: ");
			e.setPhoneNumber(input.next());
			System.out.print("Email: ");
			e.setEmail(input.next());
			//System.out.print("Password: ");
			//e.setPassword(input.next());
			e.insert();
			
			ArrayList<Customer> list = new ArrayList<Customer>();
			list = Customer.getTableAscLastName();
			System.out.println(list.size());
			//print table
			for(int i=0; i < list.size(); i++){
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
					list.get(i).getCustomerId(), list.get(i).getFirstName(), list.get(i).getLastName(), 
					list.get(i).getPhoneNumber(), list.get(i).getEmail());
			}
			*/
			/*
			ArrayList<Employee> list = new ArrayList<Employee>();
			list = Employee.getTable();
			//print table
			for(int i=0; i < list.size(); i++){
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%-15s%n",
					list.get(i).getEmployeeId(), list.get(i).getPassword(), list.get(i).getFirstName(), list.get(i).getLastName(), 
					list.get(i).getPhoneNumber(), list.get(i).getEmail());
			}
			System.out.println("Program end");
			*/
			//input.close();
			/*
			try {
				ArrayList<Inventory> list = Inventory.ReadFromFile();
				
				for( int i = 0; i<list.size(); i++) {
					list.get(i).insert();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			return;
			
		}
}
*/