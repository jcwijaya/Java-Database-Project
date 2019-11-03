package WebMart;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class MainController implements Initializable{
	//Login screen
	
	public TextField usernameText;
	public PasswordField passwordText;
	public Label message;
	public Button loginBtn;
	
	
	//Home screen
	@FXML public TabPane homeTabs;
	@FXML public Tab customersTab;
	@FXML public Tab employeesTab;
	@FXML public Tab inventoryTab;
	
	//Customers tab
	@FXML public Button updateCustomer;
	@FXML public Button insertCustomer;
	
	//Make TableView for Customer class
	@FXML TableView<Customer> customerTable;
	@FXML TableColumn<Customer, Integer> customerId;
	@FXML TableColumn<Customer, String> customerFirst;
	@FXML TableColumn<Customer, String> customerLast;
	@FXML TableColumn<Customer, String> customerPhone;
	@FXML TableColumn<Customer, String> customerEmail;
	
	//Make TableView for Employee class
	@FXML TableView<Employee> employeeTable;
	@FXML TableColumn<Employee, Integer> employeeId;
	@FXML TableColumn<Employee, String> password;
	@FXML TableColumn<Employee, String> employeeFirst;
	@FXML TableColumn<Employee, String> employeeLast;
	@FXML TableColumn<Employee, String> employeePhone;
	@FXML TableColumn<Employee, String> employeeEmail;
	
	//Make TableView for Inventory class
	@FXML TableView<Inventory> inventoryTable;
	@FXML TableColumn<Inventory, Long> productCode;
	@FXML TableColumn<Inventory, String> category;
	@FXML TableColumn<Inventory, String> name;
	@FXML TableColumn<Inventory, Double> price;
	@FXML TableColumn<Inventory, Integer> stock;
	
	//Employees tab
	
	//Inventory tab
	
	/*This method will use the hasLogin(int id, String pass) method of Employee class to check
	whether the username and password are found in the database. If it returns
	true, display a message saying that it was successful. If false, show message
	that incorrect login information was given.
	*/
	public void login(ActionEvent event) throws Exception{
		//check for valid username
		if(isInteger(usernameText.getText())) {
			int username = Integer.parseInt(usernameText.getText());
			String password = passwordText.getText();
			boolean canLogin = Employee.hasLogin(username, password);
			
			//If it is a valid username and password
			if(canLogin) {
				message.setText("Login Successful.");
				
				Stage homeStage = new Stage();
				Parent homeRoot = FXMLLoader.load(getClass().getResource("/Resource/Home.fxml"));
				Scene homeScene = new Scene(homeRoot);
				homeStage.setTitle("WebMart");
				homeStage.setScene(homeScene);
				homeStage.show();
			}
			else {
				message.setText("Login Failed.");
			}
		}
		else {
			message.setText("Login Failed.");
		}
	}
	
	//Make ObservableList from getTable method that returns ArrayList
	public ObservableList<Customer> customerList = FXCollections.observableArrayList(Customer.getTable());
	public ObservableList<Employee> employeeList = FXCollections.observableArrayList(Employee.getTable());
	public ObservableList<Inventory> inventoryList = FXCollections.observableArrayList(Inventory.getTable());
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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

	//This function checks if a string only contains digits
	public boolean isInteger(String aString) {
		if(aString.trim().isEmpty()) {
			return false;
		}
		else {
			for(int i = 0; i < aString.length(); i++) {
				if(!Character.isDigit(aString.charAt(i))){
					return false;
				}
			}
			
			return true;
		}
	}
	
}