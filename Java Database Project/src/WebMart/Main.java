package WebMart;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
		
		Parent loginRoot = FXMLLoader.load(getClass().getResource("/Resource/Login.fxml"));
		Scene loginScene = new Scene(loginRoot);
		primaryStage.setTitle("WebMart");
		primaryStage.setScene(loginScene);
		primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
