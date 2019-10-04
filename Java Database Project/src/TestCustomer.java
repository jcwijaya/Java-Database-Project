import java.sql.*;
//This program tests the Customer class.
public class TestCustomer{
	//Main method
		public static void main(String[] args) {
			Connection conn = Customer.connect();
			Customer customer = new Customer();
			customer.inputCustomerInfo(conn);
			Customer.displayTable(conn);
			System.out.println("Program end");
			return;
		}
}
