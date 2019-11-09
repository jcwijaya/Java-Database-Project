package WebMart;

import java.util.ArrayList;

public class ShoppingCart{

	private ArrayList<Inventory> cart = new ArrayList<Inventory>();		//A list of Inventory objects made using ArrayList class
	private int size = 0;												//The number of items in the cart
	
	
	//Takes in an Inventory object as parameter and adds it to the cart
	public void Add(Inventory item) {
		cart.add(item);
		size ++;
	}
	
	//Removes the item that matches what is passed in
	public void Delete(Inventory item) {
		cart.remove(item);
		size --;
	}
	
	//Adds up the price of all of the items in the cart and returns it
	public double getTotal() {
		double total = 0.0;

		Inventory cartItem = new Inventory();
		
		for(int i = 0; i < cart.size(); i++) {
			cartItem = cart.get(i);
			total += cartItem.getPrice();
		}
		return total;
	}
	
	//Returns the number of items in the cart
	public int getSize() {
		return size;
	}
	
	//Removes all items from the cart
	public void DeleteAll() {
		cart.clear();

		while(size != 0) {
			size--;
		}
	}
	
}
