package refrigerationApp.store;

import refrigerationApp.entities.Product;

import java.util.ArrayList;

public class ProductStore {
	private static final ArrayList<Product> products = new ArrayList<>();
	
	public static ArrayList<Product> getProducts() {
		return products;
	}
}
