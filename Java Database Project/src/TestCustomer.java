import java.sql.*;
import java.util.Scanner;
//This program tests the Customer class.
public class TestCustomer{
	//Main method
		public static void main(String[] args) {
			Connection conn = Customer.connect();
			//Customer customer = new Customer();
			//customer.inputCustomerInfo(conn);
			Customer.displayTable(conn);
			
			Scanner input = new Scanner(System.in);
			System.out.print("Phone number to search: ");
			String number = input.next();
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
