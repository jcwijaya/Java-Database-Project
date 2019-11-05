package WebMart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController {
	@FXML
	Button show = new Button();
		
	//Make TableView for Customer class
		@FXML public TableView<Customer> customerTable;
		@FXML public TableColumn<Customer, Integer> customerId;
		@FXML public TableColumn<Customer, String> firstName;
		@FXML public TableColumn<Customer, String> lastName;
		@FXML public TableColumn<Customer, String> phoneNumber;
		@FXML public TableColumn<Customer, String> email;
		
		//Make ObservableList from getTable method that returns ArrayList
		public ObservableList<Customer> customerList = FXCollections.observableArrayList(
				new Customer(123456, "John", "Johnson", "6123826652", "jay@aol.com"),
				new Customer(789457, "Amy", "Reed", "4738473847", "amy@gmail.com")
				);
		
		/*
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			customerId.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
			firstName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
			lastName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
			phoneNumber.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
			email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
			
			customerTable.setItems(customerList);
		}
		*/
		
		//Testing
		public void testmethod() {
			System.out.print("hello");
		}
}
