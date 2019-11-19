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
	@FXML Button connectBtn; //To test connection to database
	@FXML Button defaultBtn; //To reset textfields to default database info
	@FXML MenuItem back; //To go back to login page
	
	/*
	 * Must allow user to enter in their own database info and save it
	 * in a text file so they won't have to type it in every time.
	 * Make the values public for the connect methods.
	 */
}
