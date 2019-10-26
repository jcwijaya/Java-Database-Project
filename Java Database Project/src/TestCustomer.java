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
			//Customer c = new Customer();
			ArrayList<Customer> list = new ArrayList<Customer>();
			list = Customer.getTableAscLastName();
			System.out.println(list.size());
			//print table
			for(int i=0; i < list.size(); i++){
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
					list.get(i).getCustomerId(), list.get(i).getFirstName(), list.get(i).getLastName(), 
					list.get(i).getPhoneNumber(), list.get(i).getEmail());
			}
			
			Scanner input = new Scanner(System.in);
			System.out.print("Enter ID: ");
			int oldId = input.nextInt();
			int newId = Customer.createId();
			Customer.updateCustomerId(oldId, newId);
			list = Customer.getTableAscLastName();
			System.out.println(list.size());
			//print table
			for(int i=0; i < list.size(); i++){
				System.out.printf("%-15d%-15s%-15s%-15s%-15s%n",
					list.get(i).getCustomerId(), list.get(i).getFirstName(), list.get(i).getLastName(), 
					list.get(i).getPhoneNumber(), list.get(i).getEmail());
			}
			input.close();
			/*
			Customer.searchPhoneNumber(conn, number);
			System.out.println(Customer.hasPhoneNumber(conn, number));
			input.close();
			/*
			System.out.print("Phone number to search: ");
			String number = input.next();
			Customer.searchPhoneNumber(conn, number);
			
			System.out.print("Email to search: ");
			String emailAddress = input.next();
			Customer.searchEmail(conn, emailAddress);
			*/
			//System.out.println();
			//Customer.displayAscLastName(conn);
			//input.close();
			System.out.println("Program end");
			return;
		}
}
