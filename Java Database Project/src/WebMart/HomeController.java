package WebMart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class HomeController implements Initializable {
	//Home screen
		@FXML private TabPane homeTabs;
		@FXML private Tab customersTab;
		@FXML private Tab employeesTab;
		@FXML private Tab inventoryTab;
		
//******Customers tab components
		@FXML private Button updateCustomer; //Submits changes to database customers table
		@FXML private Label updateLbl;	//Displays message if submit is successful
		@FXML private Label messageLbl;  //Shows instructions for user
		
		//To add new customer
		@FXML private Button insertCustomer; //Adds a new customer object to customer TableView
		@FXML private TextField c_firstName; //These textfields will be used to create a new customer
		@FXML private TextField c_lastName;	//object to be added to customer TableView
		@FXML private TextField c_phoneNumber;
		@FXML private TextField c_email;
		@FXML private Button generateId; //This button calls createId method and displays the ID in a label
		@FXML private Label generateIdLbl;
		
		/*
		//CustomersTabPane
		@FXML public TabPane CustomersTabPane;
		@FXML public Tab CustomersInsertTab;
		@FXML public Tab CustomersSearchTab;
		@FXML public Tab CustomersUpdateTab;
		@FXML public Tab CustomersDeleteTab;
		*/
		
		//Make TableView for Customer class
		@FXML private TableView<Customer> customerTable;
		@FXML private TableColumn<Customer, Integer> customerId;
		@FXML private TableColumn<Customer, String> customerFirst;
		@FXML private TableColumn<Customer, String> customerLast;
		@FXML private TableColumn<Customer, String> customerPhone;
		@FXML private TableColumn<Customer, String> customerEmail;
		
		/*
//******Employees Tab components
		@FXML public TabPane EmployeesTabPane;
		@FXML public Tab EmployeesInsertTab;
		@FXML public Tab EmployeesSearchTab;
		@FXML public Tab EmployeesUpdateTab;
		@FXML public Tab EmployeesDeleteTab;
		*/
		
		@FXML private e_messageLbl;
		@FXML private Button e_save;
		@FXML private e_saveLbl;
		
		//To add an employee
		@FXML private Button e_generateId;
		@FXML private Label e_generateIdLbl;
		@FXML private TextField e_firstName;
		@FXML private TextField e_lastName;
		@FXML private TextField e_phoneNumber;
		@FXML private TextField e_email;
		
		//Make TableView for Employee class
		@FXML private TableView<Employee> employeeTable;
		@FXML private TableColumn<Employee, Integer> employeeId;
		@FXML private TableColumn<Employee, String> password;
		@FXML private TableColumn<Employee, String> employeeFirst;
		@FXML private TableColumn<Employee, String> employeeLast;
		@FXML private TableColumn<Employee, String> employeePhone;
		@FXML private TableColumn<Employee, String> employeeEmail;
		
		/*
//******Inventory Tab components
		@FXML public TabPane InventoryTabPane;
		@FXML public Tab InventoryInsertTab;
		@FXML public Tab InventorySearchTab;
		@FXML public Tab InventoryUpdateTab;
		@FXML public Tab InventoryDeleteTab;
		*/
		
		//Make TableView for Inventory class
		@FXML private TableView<Inventory> inventoryTable;
		@FXML private TableColumn<Inventory, Long> productCode;
		@FXML private TableColumn<Inventory, String> category;
		@FXML private TableColumn<Inventory, String> name;
		@FXML private TableColumn<Inventory, Double> price;
		@FXML private TableColumn<Inventory, Integer> stock;
		
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
		customerTable.setEditable(true);
		customerFirst.setCellFactory(TextFieldTableCell.forTableColumn());
		customerLast.setCellFactory(TextFieldTableCell.forTableColumn());
		customerPhone.setCellFactory(TextFieldTableCell.forTableColumn());
		customerEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		
		//Initialize Employee Table
		employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
		password.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
		employeeFirst.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
		employeeLast.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		employeePhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
		employeeEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
		
		//Make table editable
		employeeTable.setItems(employeeList);
		employeeTable.setEditable(true);
		password.setCellFactory(TextFieldTableCell.forTableColumn());
		employeeFirst.setCellFactory(TextFieldTableCell.forTableColumn());
		employeeLast.setCellFactory(TextFieldTableCell.forTableColumn());
		employeePhone.setCellFactory(TextFieldTableCell.forTableColumn());
		employeeEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		
		//Initialize Inventory table
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
		
		public void refreshCustomerTable() {
			//Make ObservableList from getTable method that returns ArrayList
			ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getTable());
			
			customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
			customerFirst.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
			customerLast.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
			customerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
			customerEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
			
			customerTable.setItems(customerList);
			
		}
		
		//Submits changes to customers table in database
		public void submitCustomers() {
			ArrayList<Customer> list = new ArrayList<Customer>();
			ObservableList<Customer> tableList = customerTable.getItems();
			
			//Get an ArrayList of objects from the table's ObservableList
			for(int i = 0; i < tableList.size(); i++) {
				list.add(tableList.get(i));
			}
			
			//Either insert or update each object into database
			for(int i = 0; i < list.size(); i++) {
				//If the customer record is already there, update it
				if(Customer.hasCustomerId(list.get(i).getCustomerId())) {
					Customer.updateFirstName(list.get(i).getCustomerId(), list.get(i).getFirstName());
					Customer.updateLastName(list.get(i).getCustomerId(), list.get(i).getLastName());
					Customer.updatePhoneNumber(list.get(i).getCustomerId(), list.get(i).getPhoneNumber());
					Customer.updateEmail(list.get(i).getCustomerId(), list.get(i).getEmail());
				}
				//If the customer record is not found, insert it
				else if(!Customer.hasCustomerId(list.get(i).getCustomerId())) {
					list.get(i).insert();
				}
				
			}
			
			updateLbl.setText("Updated successfully.");
			refreshCustomerTable();
		}
		
		//These methods are to allow user to double click on a cell
		//and update its value
		public void changeFirstNameCustomer(CellEditEvent edittedCell) {
			Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
			selectedCustomer.setFirstName(edittedCell.getNewValue().toString());
			
			updateLbl.setText("");
		}
		
		public void changeLastNameCustomer(CellEditEvent edittedCell) {
			Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
			selectedCustomer.setLastName(edittedCell.getNewValue().toString());
			updateLbl.setText("");
		}
		
		public void changePhoneCustomer(CellEditEvent edittedCell) {
			Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
			selectedCustomer.setPhoneNumber(edittedCell.getNewValue().toString());
			updateLbl.setText("");
		}
		
		public void changeEmailCustomer(CellEditEvent edittedCell) {
			Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
			selectedCustomer.setEmail(edittedCell.getNewValue().toString());
			updateLbl.setText("");
		}
		
		public void changePasswordEmployee(CellEditEvent edittedCell) {
			Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
			selectedEmployee.setPassword(edittedCell.getNewValue().toString());
		}
		
		public void changeFirstNameEmployee(CellEditEvent edittedCell) {
			Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
			selectedEmployee.setFirstName(edittedCell.getNewValue().toString());
		}
		
		public void changeLastNameEmployee(CellEditEvent edittedCell) {
			Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
			selectedEmployee.setLastName(edittedCell.getNewValue().toString());
		}
		
		public void changePhoneEmployee(CellEditEvent edittedCell) {
			Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
			selectedEmployee.setPhoneNumber(edittedCell.getNewValue().toString());
		}
		
		public void changeEmailEmployee(CellEditEvent edittedCell) {
			Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
			selectedEmployee.setEmail(edittedCell.getNewValue().toString());
		}
		
		public void generateIdCustomer() {
			generateIdLbl.setText(Integer.toString(Customer.createId()));
			messageLbl.setText("Please enter in these fields to add a customer record.");
		}
		
		//This method adds a customer record to customer TableView
		//It is not committed to database 
		public void addCustomer() {
			if(generateIdLbl.getText() != "") {
				Customer customer = new Customer(Integer.parseInt(generateIdLbl.getText()),
						c_firstName.getText(), c_lastName.getText(), c_phoneNumber.getText(), c_email.getText());
				customerTable.getItems().add(customer);
				
				generateIdLbl.setText("");
			}
			else {
				messageLbl.setText("Please fill in the ID field.");
			}
		}
		
		public void removeCustomer() {
			
		}
		
		//This method adds an employee record to employe TableView
		//It is not committed to database
		//It should also ask for and confirm password
		public void addEmployee() {
			
			if(generateIdLbl.getText() != "") {
				Customer customer = new Customer(Integer.parseInt(generateIdLbl.getText()),
						c_firstName.getText(), c_lastName.getText(), c_phoneNumber.getText(), c_email.getText());
				customerTable.getItems().add(customer);
				
				generateIdLbl.setText("");
			}
			else {
				messageLbl.setText("Please fill in the ID field.");
			}
		}

}
