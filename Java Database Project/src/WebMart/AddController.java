package WebMart;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//This is the controller for the small window that asks for
// how many items to add to the shopping cart
public class AddController {

	@FXML private Label itemLbl;
	@FXML private TextField amountTxt; //textfield to enter in how many you want of an item
	@FXML private Button addToCartBtn; //adds desired number of items to cart
	@FXML private Label stockLbl;  	   //displays message if there is not enough in stock
	@FXML private Label lbl;
	@FXML private Label totalStockLbl; //shows the total amount available in stock
	
	public static Inventory selectedItem; //the item selected from tableview to add to the cart
	Connection conn = MySql.connect();
	
	//adds items to the cart arraylist
	public void addToCart() {
		Integer amountSold = Integer.parseInt(amountTxt.getText());
		Integer newStock;
		//check if there is enough in stock
		if(amountSold <= selectedItem.getStock()) {
			//Add items to cart list
			for(int i = 0; i < amountSold; i++) {
				ShoppingCart.add(selectedItem);
			}
			//Update the stock in database and on tableview
			newStock = selectedItem.getStock() - amountSold;
			selectedItem.setStock(newStock);
			
			//search and update item in database
			Inventory.updateStock(selectedItem.getProductCode(), newStock, conn);
			
			Stage stage = (Stage) amountTxt.getScene().getWindow();
			stage.close();
			
			
			//for debugging
			System.out.println(ShoppingCart.getSize());
			for(int i = 0; i < ShoppingCart.getSize(); i++) {
				ArrayList<Inventory> cart = ShoppingCart.getCart();
				System.out.println(cart.get(i).getProductName());
			}
		}
		else {
			stockLbl.setText("Out of stock");
		}
	}
	
	/*
	 * Parameter: item that is selected on tableview
	 * Initializes the labels on AddToCart page and passes in
	 * the Inventory object that is selected
	 */
	public void initData(Inventory item) {
		itemLbl.setText(item.getProductName());
		totalStockLbl.setText(Integer.toString(item.getStock()));
		selectedItem = item;
	}
	
}
