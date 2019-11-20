package WebMart;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

//this is the controller for the page that registers new users

/*
 * This controller must be able to register and employee and store their data in the database.
 * The password and reenter password must match.
 * All fields must be filled in.
 * A random employee ID should be generated
 */
public class NewUserController {
	@FXML private Label randomID; 			//displays randomly generated employee ID
	@FXML Button enterBtn; 					//enters data typed in by user and creates new employee in database
	@FXML private TextField firstTxt;		//text field to enter first name
	@FXML private TextField lastTxt;		//text field to enter last name
	@FXML private TextField phoneTxt;		//text field to enter phone number
	@FXML private TextField emailTxt;		//text field to enter email
	@FXML private PasswordField passTxt;	//password field to enter password
	@FXML private PasswordField rePassTxt;	//password field to reenter password
}
