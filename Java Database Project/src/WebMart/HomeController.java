package WebMart;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

//This is the controller for the home page
public class HomeController implements Initializable {
	//Home screen
		@FXML private TabPane homeTabs;
		@FXML private Tab customersTab;
		@FXML private Tab employeesTab;
		@FXML private Tab inventoryTab;
		@FXML private MenuBar homeBar;
		@FXML private Menu userMenu;
		@FXML public Menu cartMenu;
		@FXML private MenuItem logout;
		@FXML private MenuItem viewCart;
		@FXML private MenuItem addItem;
		
//******Customers tab
		@FXML private Button updateCustomer; //Submits changes to database customers table
		@FXML private Label updateLbl;	//Displays message is submit is successful
		@FXML private Label messageLbl;  //Shows instructions for user
		@FXML private Button c_delete;
		@FXML private Button c_searchBtn;
		@FXML private TextField c_searchTxt;
		@FXML private Button c_refresh;
		
		//To add new customer
		@FXML private Button insertCustomer; //Adds a new customer object to customer TableView
		@FXML private TextField c_firstName; //These textfields will be used to create a new customer
		@FXML private TextField c_lastName;	//object to be added to customer TableView
		@FXML private TextField c_phoneNumber;
		@FXML private TextField c_email;
		@FXML private Button generateId; //This button calls createId method and displays the ID in a label
		@FXML private Label generateIdLbl;
		
		//Make TableView for Customer class
		@FXML private TableView<Customer> customerTable;
		@FXML private TableColumn<Customer, Integer> customerId;
		@FXML private TableColumn<Customer, String> customerFirst;
		@FXML private TableColumn<Customer, String> customerLast;
		@FXML private TableColumn<Customer, String> customerPhone;
		@FXML private TableColumn<Customer, String> customerEmail;
		
	
//******Employees Tab
		
		@FXML private Label e_messageLbl;
		@FXML private Button e_save;
		@FXML private Label e_saveLbl;
		@FXML private Button e_delete;
		@FXML private TextField e_searchTxt;
		@FXML private Button e_searchBtn;
		@FXML private Button e_refresh;
		
		//To add an employee
		@FXML private Button e_generateId;
		@FXML private Label e_generateIdLbl;
		@FXML private TextField e_firstName;
		@FXML private TextField e_lastName;
		@FXML private TextField e_phoneNumber;
		@FXML private TextField e_email;
		@FXML private TextField e_password;
		@FXML private PasswordField e_repass;
		
		//Make TableView for Employee class
		@FXML private TableView<Employee> employeeTable;
		@FXML private TableColumn<Employee, Integer> employeeId;
		@FXML private TableColumn<Employee, String> password;
		@FXML private TableColumn<Employee, String> employeeFirst;
		@FXML private TableColumn<Employee, String> employeeLast;
		@FXML private TableColumn<Employee, String> employeePhone;
		@FXML private TableColumn<Employee, String> employeeEmail;
		
		
//******Inventory Tab
		
		@FXML private Label i_messageLbl;
		@FXML private Button i_save;
		@FXML private Label i_saveLbl;
		@FXML private Button i_delete;
		@FXML private TextField i_searchTxt;
		@FXML private Button i_seachBtn;
		@FXML private Button i_refresh;
		
		//To add an inventory item
		@FXML private Button i_generateId;
		@FXML private Label i_generateIdLbl;
		@FXML private TextField i_category;
		@FXML private TextField i_productName;
		@FXML private TextField i_price;
		@FXML private TextField i_stock;
		
		//Make TableView for Inventory class
		@FXML public TableView<Inventory> inventoryTable;
		@FXML public TableColumn<Inventory, Long> productCode;
		@FXML public TableColumn<Inventory, String> category;
		@FXML public TableColumn<Inventory, String> name;
		@FXML public TableColumn<Inventory, Double> price;
		@FXML public TableColumn<Inventory, Integer> stock;
		
		Connection conn = MySql.connect();
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
		//Make ObservableList from getTable method that returns ArrayList
		 ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getTable(conn));
		 ObservableList<Employee> employeeList = FXCollections.observableArrayList(Employee.getTable(conn));
		 ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(Inventory.getTable(conn));	
		
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
		
		//Allow multiple rows to be selected
		customerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
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
		
		//Allow multiple rows to be selected
		employeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//Initialize Inventory table
		productCode.setCellValueFactory(new PropertyValueFactory<Inventory, Long>("productCode"));
		category.setCellValueFactory(new PropertyValueFactory<Inventory, String>("category"));
		name.setCellValueFactory(new PropertyValueFactory<Inventory, String>("productName"));
		price.setCellValueFactory(new PropertyValueFactory<Inventory, Double>("price"));
		stock.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("stock"));
		
		//Make table editable
		inventoryTable.setItems(inventoryList);
		inventoryTable.setEditable(true);
		category.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		stock.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		//Allow multiple rows to be selected
		inventoryTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
	}
		//Make refresh for each table individually
		public void refreshTables() {
			//Make ObservableList from getTable method that returns ArrayList
			ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getTable(conn));
			ObservableList<Employee> employeeList = FXCollections.observableArrayList(Employee.getTable(conn));
			ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(Inventory.getTable(conn));
			
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
			ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getTable(conn));
			
			customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
			customerFirst.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
			customerLast.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
			customerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
			customerEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
			
			customerTable.setItems(customerList);
			
		}
		
		public void setCustomerTable(ArrayList<Customer> list) {
			ObservableList<Customer> customerList = FXCollections.observableArrayList(list);
			
			customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
			customerFirst.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
			customerLast.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
			customerPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
			customerEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
			
			customerTable.setItems(customerList);
		}
		
		public void refreshEmployeeTable() {
			ObservableList<Employee> employeeList = FXCollections.observableArrayList(Employee.getTable(conn));

			employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
			password.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
			employeeFirst.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
			employeeLast.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
			employeePhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
			employeeEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
			
			employeeTable.setItems(employeeList);
		}
		
		public void setEmployeeTable(ArrayList<Employee> list) {
			ObservableList<Employee> employeeList = FXCollections.observableArrayList(list);

			employeeId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeId"));
			password.setCellValueFactory(new PropertyValueFactory<Employee, String>("password"));
			employeeFirst.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
			employeeLast.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
			employeePhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
			employeeEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
			
			employeeTable.setItems(employeeList);
		}
		
		public void refreshInventoryTable() {			
			ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(Inventory.getTable(conn));

			productCode.setCellValueFactory(new PropertyValueFactory<Inventory, Long>("productCode"));
			category.setCellValueFactory(new PropertyValueFactory<Inventory, String>("category"));
			name.setCellValueFactory(new PropertyValueFactory<Inventory, String>("productName"));
			price.setCellValueFactory(new PropertyValueFactory<Inventory, Double>("price"));
			stock.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("stock"));
			
			inventoryTable.setItems(inventoryList);
		}
		
		public void setInventoryTable(ArrayList<Inventory> list) {
			ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(list);

			productCode.setCellValueFactory(new PropertyValueFactory<Inventory, Long>("productCode"));
			category.setCellValueFactory(new PropertyValueFactory<Inventory, String>("category"));
			name.setCellValueFactory(new PropertyValueFactory<Inventory, String>("productName"));
			price.setCellValueFactory(new PropertyValueFactory<Inventory, Double>("price"));
			stock.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("stock"));
			
			inventoryTable.setItems(inventoryList);
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
				if(Customer.hasCustomerId(list.get(i).getCustomerId(), conn)) {
					Customer.updateExistingCustomer(list.get(i).getCustomerId(), list.get(i).getFirstName(),
							list.get(i).getLastName(), list.get(i).getPhoneNumber(), list.get(i).getEmail(), conn);
				}
				//If the customer record is not found, insert it
				else if(!Customer.hasCustomerId(list.get(i).getCustomerId(), conn)) {
					list.get(i).insert(conn);
				}
				
			}
			
			updateLbl.setText("Updated successfully.");
			
			//blanking out text fields
			c_firstName.setText("");
			c_lastName.setText("");
			c_phoneNumber.setText("");
			c_email.setText("");
			generateIdLbl.setText("");
			
			
			refreshCustomerTable();
		}
		
		//Submits changes to employees table in database
		public void submitEmployees() {
			ArrayList<Employee> list = new ArrayList<Employee>();
			ObservableList<Employee> tableList = employeeTable.getItems();
			
			//Get an ArrayList of objects from the table's ObservableList
			for(int i = 0; i < tableList.size(); i++) {
				list.add(tableList.get(i));
			}
			
			//Either insert or update each object into database
			for(int i = 0; i < list.size(); i++) {
				//If the employee record is already there, update it
				if(Employee.hasEmployeeId(list.get(i).getEmployeeId(), conn)) {
					Employee.updateExistingEmployee(list.get(i).getEmployeeId(), list.get(i).getPassword(), 
					list.get(i).getFirstName(), list.get(i).getLastName(), list.get(i).getPhoneNumber(), list.get(i).getEmail(), conn);
				}
				//If the employee record is not found, insert it
				else if(!Employee.hasEmployeeId(list.get(i).getEmployeeId(), conn)) {
					list.get(i).insert(conn);
				}
			}
			
			e_saveLbl.setText("Updated successfully.");
			
			//blanking out text fields
			e_generateIdLbl.setText("");
			e_firstName.setText("");
			e_lastName.setText("");
			e_phoneNumber.setText("");
			e_email.setText("");
			e_password.setText("");
			e_repass.setText("");
			
			refreshEmployeeTable();
		}	
		
		//Submits changes to inventory table in database
		public void submitInventory() {
			ArrayList<Inventory> list = new ArrayList<Inventory>();
			ObservableList<Inventory> tableList = inventoryTable.getItems();
			
			//Get an ArrayList of objects from the table's ObservableList
			for(int i = 0; i < tableList.size(); i++) {
				list.add(tableList.get(i));
			}
			
			//Either insert or update each object into database
			for(int i = 0; i < list.size(); i++) {
				//If the inventory record is already there, update it
				if(Inventory.hasProductCode(list.get(i).getProductCode(), conn)) {
					Inventory.updateExistingItem(list.get(i).getProductCode(), list.get(i).getCategory(),
					list.get(i).getProductName(), list.get(i).getPrice(), list.get(i).getStock(), conn);
				}
				//If the inventory record is not found, insert it
				else if(!Inventory.hasProductCode(list.get(i).getProductCode(), conn)) {
					list.get(i).insert(conn);
				}
				
			}
			
			i_saveLbl.setText("Updated successfully.");
			
			//blanking out text fields
			i_generateIdLbl.setText("");
			i_category.setText("");
			i_productName.setText("");
			i_price.setText("");
			i_stock.setText("");
			
			refreshInventoryTable();
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
			
			e_saveLbl.setText("");
		}
		
		public void changeFirstNameEmployee(CellEditEvent edittedCell) {
			Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
			selectedEmployee.setFirstName(edittedCell.getNewValue().toString());
			
			e_saveLbl.setText("");

		}
		
		public void changeLastNameEmployee(CellEditEvent edittedCell) {
			Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
			selectedEmployee.setLastName(edittedCell.getNewValue().toString());
			
			e_saveLbl.setText("");

		}
		
		public void changePhoneEmployee(CellEditEvent edittedCell) {
			Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
			selectedEmployee.setPhoneNumber(edittedCell.getNewValue().toString());
			
			e_saveLbl.setText("");

		}
		
		public void changeEmailEmployee(CellEditEvent edittedCell) {
			Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
			selectedEmployee.setEmail(edittedCell.getNewValue().toString());
			
			e_saveLbl.setText("");

		}
		
		public void changeCategoryInventory(CellEditEvent edittedCell) {
			Inventory selectedInventory = inventoryTable.getSelectionModel().getSelectedItem();
			selectedInventory.setCategory(edittedCell.getNewValue().toString());
			
			i_saveLbl.setText("");
		}
		
		public void changeProductNameInventory(CellEditEvent edittedCell) {
			Inventory selectedInventory = inventoryTable.getSelectionModel().getSelectedItem();
			selectedInventory.setProductName(edittedCell.getNewValue().toString());
			
			i_saveLbl.setText("");
		}
		
		public void changePriceInventory(CellEditEvent edittedCell) {
			Inventory selectedInventory = inventoryTable.getSelectionModel().getSelectedItem();
			selectedInventory.setPrice(Double.parseDouble(edittedCell.getNewValue().toString()));
			
			i_saveLbl.setText("");
		}
		
		public void changeStockInventory(CellEditEvent edittedCell) {
			Inventory selectedInventory = inventoryTable.getSelectionModel().getSelectedItem();
			selectedInventory.setStock(Integer.parseInt(edittedCell.getNewValue().toString()));
			
			i_saveLbl.setText("");
		}
		
		public void generateIdCustomer() {
			generateIdLbl.setText(Integer.toString(Customer.createId(conn)));
		}
		
		public void generateIdEmployee() {
			e_generateIdLbl.setText(Integer.toString(Employee.createId(conn)));
		}
		
		public void generateCodeInventory() {
			i_generateIdLbl.setText(Long.toString(Inventory.createId(conn)));
		}
		
		
		//This method adds a customer record to customer TableView
		//It is not committed to database 
		public void addCustomer() {
			if(generateIdLbl.getText() != "") {
				Customer customer = new Customer(Integer.parseInt(generateIdLbl.getText()),
						c_firstName.getText(), c_lastName.getText(), c_phoneNumber.getText(), c_email.getText());
				customerTable.getItems().add(customer);
				
				generateIdLbl.setText("");
				updateLbl.setText("");
				messageLbl.setText("Please enter in these fields to add a customer record.");
			}
			else {
				messageLbl.setText("Please fill in the necessary fields.");
			}
		}
		
		//This method adds an employee record to employee TableView
		//It is not committed to database yet
		public void addEmployee() {
			//Only add to table if ID is filled out
			String pass = e_password.getText();
			String repass = e_repass.getText();
			if((e_generateIdLbl.getText() != "") && (passMatch(pass, repass))) {
				Employee employee = new Employee(Integer.parseInt(e_generateIdLbl.getText()),
						e_password.getText(), e_firstName.getText(), e_lastName.getText(),
						e_phoneNumber.getText(), e_email.getText());
				employeeTable.getItems().add(employee);
					
				e_generateIdLbl.setText("");
				e_saveLbl.setText("");
				e_messageLbl.setText("Please enter in these fields to add an employee record.");
			}
			else {
				e_messageLbl.setText("Please fill in the necessary fields.");
			}
		}
		
		//This method adds an inventory record to inventory TableView
		//It is not committed to database yet
		public void addInventory() {
			//Only add to table if ID is filled out
			if(i_generateIdLbl.getText() != "") {
				Inventory inventory = new Inventory(Long.parseLong(i_generateIdLbl.getText()),
						i_category.getText(), i_productName.getText(), Double.parseDouble(i_price.getText()),
						Integer.parseInt(i_stock.getText()));
				inventoryTable.getItems().add(inventory);
					
				i_generateIdLbl.setText("");
				i_saveLbl.setText("");
				i_messageLbl.setText("Please enter in these fields to add an inventory record.");
			}
			else {
				i_messageLbl.setText("Please fill in the necessary fields.");
			}
		}
		
		
		//This method removes selected customers from TableView and database
		public void removeCustomer() {
			ObservableList<Customer> selectedCustomers, allCustomers;
			allCustomers = customerTable.getItems();
			selectedCustomers = customerTable.getSelectionModel().getSelectedItems();
			
			for(int i = 0; i < selectedCustomers.size(); i++) {
				Customer customer = selectedCustomers.get(i);
				//Remove from ObservableList
				allCustomers.remove(customer);
				updateLbl.setText("");
				//If the record is in database, delete it
				if(Customer.hasCustomerId(customer.getCustomerId(), conn)) {
					customer.delete(conn);
				}
				
			}
		}

		public void removeEmployee() {
			ObservableList<Employee> selectedEmployees, allEmployees;
			allEmployees = employeeTable.getItems();
			selectedEmployees = employeeTable.getSelectionModel().getSelectedItems();
			
			for(int i = 0; i < selectedEmployees.size(); i++) {
				Employee employee = selectedEmployees.get(i);
				//Remove from ObservableList
				allEmployees.remove(employee);
				e_saveLbl.setText("");
				//If the record is in database, delete it
				if(Employee.hasEmployeeId(employee.getEmployeeId(), conn)) {
					employee.delete(conn);
				}
				
			}
		}
		
		//This method removes selected customers from TableView and database
		public void removeInventory() {
			ObservableList<Inventory> selectedItems, allItems;
			allItems = inventoryTable.getItems();
			selectedItems = inventoryTable.getSelectionModel().getSelectedItems();
			
			for(int i = 0; i < selectedItems.size(); i++) {
				Inventory inventory = selectedItems.get(i);
				//Remove from ObservableList
				allItems.remove(inventory);
				i_saveLbl.setText("");
				//If the record is in database, delete it
				if(Inventory.hasProductCode(inventory.getProductCode(), conn)) {
					inventory.delete(conn);
				}
				
			}
		}
		
//The following three methods allow the user to search for a word in each of the tableviews
		public void searchCustomers() {
			refreshCustomerTable();
			ObservableList<Customer> list = customerTable.getItems();
			ArrayList<Customer> found = new ArrayList<Customer>();
			String str = c_searchTxt.getText();
			
			//Check if str is a substring in each record
			for(int i = 0; i < list.size(); i++) {
				if(Integer.toString(list.get(i).getCustomerId()).toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getFirstName().toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getLastName().toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getPhoneNumber().toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getEmail().toLowerCase().contains(str.toLowerCase())) {
					found.add(list.get(i));
				}
			}
			//Put found onto tableview
			setCustomerTable(found);

		}
		public void searchEmployees() {
			refreshEmployeeTable();
			ObservableList<Employee> list = employeeTable.getItems();
			ArrayList<Employee> found = new ArrayList<Employee>();
			String str = e_searchTxt.getText();
			
			//Check if str is a substring in each record
			for(int i = 0; i < list.size(); i++) {
				if(Integer.toString(list.get(i).getEmployeeId()).toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getFirstName().toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getLastName().toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getPhoneNumber().toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getEmail().toLowerCase().contains(str.toLowerCase())) {
					found.add(list.get(i));
				}
			}
			//Put found onto tableview
			setEmployeeTable(found);

		}		
		
		public void searchInventory() {
			refreshInventoryTable();
			ObservableList<Inventory> list = inventoryTable.getItems();
			ArrayList<Inventory> found = new ArrayList<Inventory>();
			String str = i_searchTxt.getText();
		
			for(int i = 0; i < list.size(); i++) {
				if(Long.toString(list.get(i).getProductCode()).toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getCategory().toLowerCase().contains(str.toLowerCase()) ||
					list.get(i).getProductName().toLowerCase().contains(str.toLowerCase()) ||
					Double.toString(list.get(i).getPrice()).toLowerCase().contains(str.toLowerCase()) ||
					Integer.toString(list.get(i).getStock()).toLowerCase().contains(str.toLowerCase())){
					found.add(list.get(i));
					
				}
			}
			setInventoryTable(found);
		}
		
		//This method adds the selected inventory item to the shopping cart
		//It should make a dialog box pop up to ask for amount to add and limit
		//amount if not enough in stock.
		public void showAddToCartPage() {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Resource/AddToCart.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);
				
				AddController addController = loader.getController();
				addController.initData(inventoryTable.getSelectionModel().getSelectedItem());
				
				Stage stage = new Stage();
				stage.setTitle("WebMart");
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void logout() {
			try {
				Parent loginRoot = FXMLLoader.load(getClass().getResource("/Resource/Login.fxml"));
				Scene loginScene = new Scene(loginRoot);
				Stage loginStage = new Stage();
				loginStage.setTitle("WebMart");
				loginStage.setScene(loginScene);
				loginStage.show();
				Stage homeStage = (Stage) homeBar.getScene().getWindow();
				homeStage.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//this method returns true if the passwords match
		public boolean passMatch(String p, String rp) {
			if (p.equals(rp)) {
				return true;
			}
			else {
				e_password.setText("");
				e_repass.setText("");
				return false;
			}
		}

		//changes to cart page
		public void toCartPage() {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/Resource/Cart.fxml"));
				Parent root = loader.load();
				Scene scene = new Scene(root);			
				Stage stage = new Stage();
				stage.setTitle("WebMart");
				stage.setScene(scene);
				stage.show();
				
				Stage homeStage = (Stage) homeBar.getScene().getWindow();
				homeStage.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
