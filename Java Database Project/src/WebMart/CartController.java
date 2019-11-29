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

public class CartController implements Initializable{

	//items on cart page
	@FXML private MenuBar menu;
	@FXML private Menu cartMenu;
	@FXML public MenuItem back;
	@FXML private Label total_lbl;
	@FXML private TableView<Inventory> cart;
	@FXML private TableColumn<Inventory, Integer> cartCode;
	@FXML private TableColumn<Inventory, String> cartCat;
	@FXML private TableColumn<Inventory, String> cartName;
	@FXML private TableColumn<Inventory, String> cartPrice;
	@FXML private Button btnCheckout;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Make ObservableList from getTable method that returns ArrayList
		ObservableList<Inventory> shoppingList = FXCollections.observableArrayList(ShoppingCart.getCart());
	
		//Initialize cart table
		cartCode.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("productCode"));
		cartCat.setCellValueFactory(new PropertyValueFactory<Inventory, String>("category"));
		cartName.setCellValueFactory(new PropertyValueFactory<Inventory, String>("productName"));
		cartPrice.setCellValueFactory(new PropertyValueFactory<Inventory, String>("price"));
		
		cart.setItems(shoppingList);
		
		total_lbl.setText("Total: " + ShoppingCart.getTotal());
	}
	
	
	//empties shopping cart to simulate checkout
	public void checkout() {
		ShoppingCart.DeleteAll();
		
		ObservableList<Inventory> empty = FXCollections.observableArrayList(ShoppingCart.getCart());
		
		cart.setItems(empty);
		
		total_lbl.setText("Total: ");
	}
	
	public void toHomePage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Resource/Home.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);			
			Stage stage = new Stage();
			stage.setTitle("WebMart");
			stage.setScene(scene);
			stage.show();
			
			Stage homeStage = (Stage) menu.getScene().getWindow();
			homeStage.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
