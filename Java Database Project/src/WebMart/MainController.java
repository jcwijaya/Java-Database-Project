package WebMart;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class MainController {
	
	//Login screen
	@FXML public TextField usernameText;
	@FXML public PasswordField passwordText;
	@FXML public Label message;
	@FXML public Button loginBtn;
	@FXML public Button createBtn;
	
	
	/*This method will use the hasLogin(int id, String pass) method of Employee class to check
	whether the username and password are found in the database. If it returns
	true, display a message saying that it was successful. If false, show message
	that incorrect login information was given.
	*/
	public void login(ActionEvent event) throws Exception{
		MySql.readInfoFromFile();
		Connection conn = MySql.connect();
		//check for valid username
		if(isInteger(usernameText.getText())) {
			int username = Integer.parseInt(usernameText.getText());
			String password = passwordText.getText();
			boolean canLogin = Employee.hasLogin(username, password, conn);
			
			//If it is a valid username and password
			if(canLogin) {
				message.setText("Login Successful.");
				
				Stage homeStage = new Stage();
				Parent homeRoot = FXMLLoader.load(getClass().getResource("/Resource/Home.fxml"));
				Scene homeScene = new Scene(homeRoot);
				homeStage.setTitle("WebMart");
				homeStage.setScene(homeScene);
				homeStage.show();
				Stage loginStage = (Stage) loginBtn.getScene().getWindow();
				loginStage.close();
			}
			else {
				message.setText("Login Failed.");
			}
		}
		else {
			message.setText("Login Failed.");
		}
	}
	
	public void toSetupPage() {
		try {
		Stage setupStage = new Stage();
		Parent setupRoot = FXMLLoader.load(getClass().getResource("/Resource/Setup.fxml"));
		Scene setupScene = new Scene(setupRoot);
		setupStage.setTitle("WebMart");
		setupStage.setScene(setupScene);
		setupStage.show();
		Stage loginStage = (Stage) loginBtn.getScene().getWindow();
		loginStage.close();
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Also use this as controller for NewUser.fxml that creates new users
	 * for webmart.
	 */
	
	//this opens the NewUser window and closes the login screen
	public void toNewUserPage() {
		try {
		Parent newUserRoot = FXMLLoader.load(getClass().getResource("/Resource/NewUser.fxml"));
		Scene newUserScene = new Scene(newUserRoot);
		Stage newUserStage = new Stage();
		newUserStage.setTitle("WebMart");
		newUserStage.setScene(newUserScene);
		newUserStage.show();
		Stage loginStage = (Stage) createBtn.getScene().getWindow();
		loginStage.close();
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
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