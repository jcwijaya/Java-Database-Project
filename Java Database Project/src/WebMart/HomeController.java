package WebMart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class HomeController implements Initializable {
	//Home screen
		@FXML public TabPane homeTabs;
		@FXML public Tab customersTab;
		@FXML public Tab employeesTab;
		@FXML public Tab inventoryTab;
		
		//Customers tab
		@FXML public Button updateCustomer;
		@FXML public Button insertCustomer;
		
		/*
		//CustomersTabPane
		@FXML public TabPane CustomersTabPane;
		@FXML public Tab CustomersInsertTab;
		@FXML public Tab CustomersSearchTab;
		@FXML public Tab CustomersUpdateTab;
		@FXML public Tab CustomersDeleteTab;
		*/
		
		//Make TableView for Customer class
		@FXML public TableView<Customer> customerTable;
		@FXML public TableColumn<Customer, Integer> customerId;
		@FXML public TableColumn<Customer, String> customerFirst;
		@FXML public TableColumn<Customer, String> customerLast;
		@FXML public TableColumn<Customer, String> customerPhone;
		@FXML TableColumn<Customer, String> customerEmail;
		
		/*
		//Employees Tab
		@FXML public TabPane EmployeesTabPane;
		@FXML public Tab EmployeesInsertTab;
		@FXML public Tab EmployeesSearchTab;
		@FXML public Tab EmployeesUpdateTab;
		@FXML public Tab EmployeesDeleteTab;
		*/
		
		//Make TableView for Employee class
		@FXML public TableView<Employee> employeeTable;
		@FXML public TableColumn<Employee, Integer> employeeId;
		@FXML public TableColumn<Employee, String> password;
		@FXML public TableColumn<Employee, String> employeeFirst;
		@FXML public TableColumn<Employee, String> employeeLast;
		@FXML public TableColumn<Employee, String> employeePhone;
		@FXML public TableColumn<Employee, String> employeeEmail;
		
		/*
		//Inventory Tab
		@FXML public TabPane InventoryTabPane;
		@FXML public Tab InventoryInsertTab;
		@FXML public Tab InventorySearchTab;
		@FXML public Tab InventoryUpdateTab;
		@FXML public Tab InventoryDeleteTab;
		*/
		
		//Make TableView for Inventory class
		@FXML public TableView<Inventory> inventoryTable;
		@FXML public TableColumn<Inventory, Long> productCode;
		@FXML public TableColumn<Inventory, String> category;
		@FXML public TableColumn<Inventory, String> name;
		@FXML public TableColumn<Inventory, Double> price;
		@FXML public TableColumn<Inventory, Integer> stock;
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
		//Make ObservableList from getTable method that returns ArrayList
		 ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getTable());
		 ObservableList<Employee> employeeList = FXCollections.observableArrayList(Employee.getTable());
		 ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(Inventory.getTable());	
		
		 //Initialize Customer table
		customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
		customerFirst.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
		customerLast.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		customerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
		customerEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
		
		
		//Make table editable
		customerTable.setItems(customerList);
		/*
		customerTable.setEditable(true);
		customerId.setCellFactory(TextFieldTableCell.forTableColumn());
		*/
		
		employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
		password.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
		employeeFirst.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
		employeeLast.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		employeePhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
		employeeEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
		
		employeeTable.setItems(employeeList);
		
		productCode.setCellValueFactory(new PropertyValueFactory<Inventory, Long>("productCode"));
		category.setCellValueFactory(new PropertyValueFactory<Inventory, String>("category"));
		name.setCellValueFactory(new PropertyValueFactory<Inventory, String>("productName"));
		price.setCellValueFactory(new PropertyValueFactory<Inventory, Double>("price"));
		stock.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("stock"));
		
		inventoryTable.setItems(inventoryList);
		
	}
		//Make refresh for each table individually
		public void refreshTables() {
			//Make ObservableList from getTable method that returns ArrayList
			ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getTable());
			ObservableList<Employee> employeeList = FXCollections.observableArrayList(Employee.getTable());
			ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(Inventory.getTable());
			
			customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
			customerFirst.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
			customerLast.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
			customerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
			customerEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
			
			customerTable.setItems(customerList);
			
			employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
			password.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
			employeeFirst.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
			employeeLast.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
			employeePhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
			employeeEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
			
			employeeTable.setItems(employeeList);
			
			productCode.setCellValueFactory(new PropertyValueFactory<Inventory, Long>("productCode"));
			category.setCellValueFactory(new PropertyValueFactory<Inventory, String>("category"));
			name.setCellValueFactory(new PropertyValueFactory<Inventory, String>("productName"));
			price.setCellValueFactory(new PropertyValueFactory<Inventory, Double>("price"));
			stock.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("stock"));
			
			inventoryTable.setItems(inventoryList);
		}
	/*
		public void insertCustomer() {
			Customer customer = new Customer();
			customer.setCustomerId(Customer.createId());
			customer.setFirstName();
			customer.setLastName();
			customer.setPhoneNumber();
			customer.setEmail();
			customer.insert();
		}
	*/

}
