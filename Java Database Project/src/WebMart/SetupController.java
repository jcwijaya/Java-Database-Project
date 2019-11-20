package WebMart;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

//This is the controller for the database setup page
public class SetupController {

	@FXML private TextField driverTxt; //database driver
	@FXML private TextField urlTxt; //database url
	@FXML private TextField userTxt; //database username
	@FXML private PasswordField passwordTxt; //database password
	@FXML private Label testLbl; //Displays message
	@FXML private Button connectBtn; //To test connection to database
	@FXML private Button defaultBtn; //To reset textfields to default database info
	@FXML private MenuItem back; //To go back to login page
	
	/*
	 * Must allow user to enter in their own database info and save it
	 * in a text file so they won't have to type it in every time.
	 * Make the values public for the connect methods.
	 */
	
	//Make initalize method that puts into the textfields the custom db info
	//the user previously put in.
	//!!!May need to make method inside of customer etc. classes to change login
	//or make a new class for logging in.
	
	//This method changes the database login information
	public void setDatabaseLogin() {
		MySql.setDriver(driverTxt.getText());
		MySql.setUrl(urlTxt.getText());
		MySql.setDbUser(userTxt.getText());
		MySql.setDbPassword(passwordTxt.getText());
		
		//Save new login to text file
	}
	
	public void resetDefault() {
		driverTxt.setText("com.mysql.jdbc.Driver");
		urlTxt.setText("jdbc:mysql://localhost/test?autoReconnect=true&useSSL=false");
		userTxt.setText("customer1");
		passwordTxt.setText("#mtsu");
		
		setDatabaseLogin();
	}
	
}
