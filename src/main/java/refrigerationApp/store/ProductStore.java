package refrigerationApp.store;

import refrigerationApp.domain.Product;

import java.util.ArrayList;

public class ProductStore {
    static ArrayList<Product> products = new ArrayList<>();

    public static ArrayList<Product> getProducts() {
        return products;
    }
}
