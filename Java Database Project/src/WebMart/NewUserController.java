package WebMart;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * This controller must be able to register and employee and store their data in the database.
 * The password and reenter password must match.
 * All fields must be filled in.
 * A random employee ID should be generated
 */
public class NewUserController {
	@FXML private Label randomID; 			//displays randomly generated employee ID
	@FXML private Label statusLabel;		//this label will show if the registration is successful or not
	@FXML Button enterBtn; 					//enters data typed in by user and creates new employee in database
	@FXML Button returnBtn;					//this button returns to the login screen when clicked
	@FXML private TextField firstTxt;		//text field to enter first name
	@FXML private TextField lastTxt;		//text field to enter last name
	@FXML private TextField phoneTxt;		//text field to enter phone number
	@FXML private TextField emailTxt;		//text field to enter email
	@FXML private PasswordField passTxt;	//password field to enter password
	@FXML private PasswordField rePassTxt;	//password field to reenter password
	
	public void register(ActionEvent event) throws Exception{
		//variable declarations and assignments
		String firstName = firstTxt.getText();
		String lastName = lastTxt.getText();
		String phoneNum = phoneTxt.getText();
		String email = emailTxt.getText();
		String pass = passTxt.getText();
		String rePass = rePassTxt.getText();
		int newID;
		String strNewID;
		
		//checks to see if all fields are full and if passwords matched
		//if both are true, employee is added to database and status is displayed
		//text fields are made blank after
		if (notEmpty(firstName, lastName, phoneNum, email, pass, rePass) && passMatch(pass, rePass)) {
			//employee ID is generated and displayed
			newID = Employee.createId();
			strNewID = Integer.toString(newID);
			randomID.setText(strNewID);
			
			//inserting employee into database
			Employee newEmployee = new Employee(newID, pass, firstName, lastName, phoneNum, email);
			newEmployee.insert();
			
			//status is updated
			statusLabel.setText("Status: Registration successful");
			
			//blanking text fields
			firstTxt.setText("");
			lastTxt.setText("");
			phoneTxt.setText("");
			emailTxt.setText("");
			passTxt.setText("");
			rePassTxt.setText("");
		}
		//otherwise failure message is displayed
		else {
			statusLabel.setText("Status: Registration failed");
		}
		
	}
	
	//this takes the user back to the login page when pressing the button
	public void toLoginPage() {
		try {
			Stage loginStage = new Stage();
			Parent loginRoot = FXMLLoader.load(getClass().getResource("/Resource/Login.fxml"));
			Scene loginScene = new Scene(loginRoot);
			loginStage.setTitle("WebMart");
			loginStage.setScene(loginScene);
			loginStage.show();
			Stage newUserStage = (Stage) returnBtn.getScene().getWindow();
			newUserStage.close();
			
			}
			catch(IOException e) {
				e.printStackTrace();
			}
	}
	

	//this method returns true if the passwords match
	public boolean passMatch(String p, String rp) {
		if (p == rp) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean notEmpty(String f, String l, String p, String e, String pass, String rpass) {
		if ((f != null) &&
			(l != null) &&
			(p != null) &&
			(e != null) &&
			(pass != null) &&
			(rpass != null) &&
			(!f.isEmpty()) &&
			(!l.isEmpty()) &&
			(!p.isEmpty()) &&
			(!e.isEmpty()) &&
			(!pass.isEmpty()) &&
			(!rpass.isEmpty())) {
				return true;
		}
		else {
			return false;
		}
	}
	
	
}


