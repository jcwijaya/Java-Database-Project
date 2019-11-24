package WebMart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddController implements Initializable {

	@FXML private Label itemLbl;
	@FXML private TextField amountTxt;
	@FXML private Button addToCartBtn;
	@FXML private Label stockLbl;
	@FXML private Label totalStockLbl;
	
	public static Inventory selectedItem;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	public void addToCart() {
		//check if there is enough in stock
		if(Integer.parseInt(amountTxt.getText()) <= selectedItem.getStock()) {
			//Add items to cart list
			for(int i = 0; i < Integer.parseInt(amountTxt.getText()); i++) {
				ShoppingCart.add(selectedItem);
			}
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
	
	public void initData(Inventory item) {
		itemLbl.setText(item.getProductName());
		totalStockLbl.setText(Integer.toString(item.getStock()));
		selectedItem = item;
	}
	
}
