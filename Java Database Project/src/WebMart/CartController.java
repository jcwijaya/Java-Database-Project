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
	@FXML public Menu back;
	@FXML private Label total_lbl;
	@FXML private TableView<ShoppingCart> cart;
	@FXML private TableColumn<ShoppingCart, Integer> cartCode;
	@FXML private TableColumn<ShoppingCart, String> cartCat;
	@FXML private TableColumn<ShoppingCart, String> cartName;
	@FXML private TableColumn<ShoppingCart, String> cartPrice;

	
	//This method removes the selected item from the cart
	public void removeFromCart() {
		
	}
	
	//This method will update the inventory stock of the items
	//in the shopping cart
	//Must make methods in Inventory class to update stock
	//and give notification if out of stock
	public void checkout() {
		
	}
}
