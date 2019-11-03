package WebMart;
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

//This program tests the Customer class.
public class TestCustomer{
	//Main method
		public static void main(String[] args) {
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
			ArrayList<Employee> list = new ArrayList<Employee>();
			list = Employee.getTable();
			//print table
			for(int i=0; i < list.size(); i++){
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%-15s%n",
					list.get(i).getEmployeeId(), list.get(i).getPassword(), list.get(i).getFirstName(), list.get(i).getLastName(), 
					list.get(i).getPhoneNumber(), list.get(i).getEmail());
			}
			System.out.println("Program end");
			//input.close();
			return;
			
		}
}
