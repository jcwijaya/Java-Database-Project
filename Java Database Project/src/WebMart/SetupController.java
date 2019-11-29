package WebMart;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//This is the controller for the database setup page
public class SetupController implements Initializable{

	@FXML private TextField driverTxt; //database driver
	@FXML private TextField urlTxt; //database url
	@FXML private TextField userTxt; //database username
	@FXML private PasswordField passwordTxt; //database password
	@FXML private Label testLbl; //Displays message
	@FXML private Label dbMessageLbl;
	@FXML private Button connectBtn; //To test connection to database
	@FXML private Button defaultBtn; //To reset textfields to default database info
	@FXML private Button saveDbLoginBtn;
	@FXML private MenuItem back; //To go back to login page
	
	/*
	 * Must allow user to enter in their own database info and save it
	 * in a text file so they won't have to type it in every time.
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//get database info that is saved inside of mysql_info text file
		MySql.readInfoFromFile();
		driverTxt.setText(MySql.getDriver());
		urlTxt.setText(MySql.getUrl());
		userTxt.setText(MySql.getDbUser());
		passwordTxt.setText(MySql.getDbPassword());
		
	}
	
	//This method changes the database login information and saves it to the file
	public void setDatabaseLogin() {
		MySql.setDriver(driverTxt.getText());
		MySql.setUrl(urlTxt.getText());
		MySql.setDbUser(userTxt.getText());
		MySql.setDbPassword(passwordTxt.getText());
		
		//Save the database info to mysql_info file
		MySql.writeInfoToFile(driverTxt.getText(), urlTxt.getText(), userTxt.getText(), passwordTxt.getText());
		dbMessageLbl.setText("Please enter in your database information or use default.");
		testLbl.setText("");
	} 
	
	//This method resets the database login information to the default
	//values given with the program and saves it to the file
	public void resetDefault() {
		driverTxt.setText("com.mysql.jdbc.Driver");
		urlTxt.setText("jdbc:mysql://localhost/test?autoReconnect=true&useSSL=false");
		userTxt.setText("customer1");
		passwordTxt.setText("#mtsu");
		
		setDatabaseLogin();
	}
	
	//This method tests whether the program can connect to local database
	//with current saved login
	public void testConnection() {
		setDatabaseLogin();
		
		if(MySql.connect() != null) {
			testLbl.setText("Connection successful");
			dbMessageLbl.setText("Please enter in your database information or use default.");
		}
		else if(MySql.connect() == null) {
			testLbl.setText("Connection failed");
			dbMessageLbl.setText("Please enter in your database information or use default.");
		}
	
	}
	
	//Returns to login page
	public void logout() {
		try {
			Parent loginRoot = FXMLLoader.load(getClass().getResource("/Resource/Login.fxml"));
			Scene loginScene = new Scene(loginRoot);
			Stage loginStage = new Stage();
			loginStage.setTitle("WebMart");
			loginStage.setScene(loginScene);
			loginStage.show();
			Stage setupStage = (Stage) testLbl.getScene().getWindow();
			setupStage.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
