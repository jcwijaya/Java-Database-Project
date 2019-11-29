package WebMart;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Scanner;

public class InitializeInventory {

	//This method will read and insert a list of inventory items from a text file
	public static void main(String[] args) {
		try {
			MySql.readInfoFromFile();
			Connection conn = MySql.connect();
			File file = new File("items");
			Scanner scanner = new Scanner(file);
			String strProductCode;
			String category;
			String productName;
			String strPrice;
			String strStock;
			double price;
			int stock;
			Long productCode;
			
			while(scanner.hasNext())
			{
				scanner.useDelimiter(";");
				strProductCode = scanner.next();
				productCode = Long.parseLong(strProductCode.trim());
				category = scanner.next();
				productName = scanner.next();
				strPrice = scanner.next();
				price = Double.parseDouble(strPrice.trim());
				strStock = scanner.next();
				stock = Integer.parseInt(strStock.trim());
			
				Inventory item = new Inventory(productCode, category.trim(), productName.trim(), price, stock);
				if(!Inventory.hasProductCode(item.getProductCode(), conn)) {
					item.insert(conn);
				}

				System.out.println(item.getProductCode() + " Insertion successful.");
				}
				scanner.close();
			
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return;
	}
}
