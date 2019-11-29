package WebMart;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShoppingCart{

	private static ArrayList<Inventory> cart = new ArrayList<Inventory>();	//list of Inventory objects
	private static int size = 0;											//The number of items in the cart
	
	//Takes in an Inventory object as parameter and adds it to the cart
	public static void add(Inventory item) {
		cart.add(item);
		size ++;
	}
	
	//Removes the item that matches what is passed in
	public static void delete(Inventory item) {
		cart.remove(item);
		size --;
	}
	
	//Adds up the price of all of the items in the cart and returns it
	public static double getTotal() {
		double total = 0.0;

		//Needs Inventory.getPrice();
		Inventory cartItem = new Inventory();
		
		for(int i = 0; i < cart.size(); i++) {
			cartItem = cart.get(i);
			total += cartItem.getPrice();
		}
		return total;
	}
	
	public static ArrayList<Inventory> getCart(){
		return cart;
	}
	
	//Returns the number of items in the cart
	public static int getSize() {
		return size;
	}
	
	//Removes all items from the cart
	public static void DeleteAll() {
		cart.clear();

		while(size != 0) {
			size--;
		}
	}
	
}
