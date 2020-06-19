package refrigerationApp.entities;

public class Product {
	private static int    counter = 0;
	private        String productTitle;
	private        int    productId;
	
	public Product() {
	}
	
	public Product(String title) {
		this.productId    = counter++;
		this.productTitle = title.toLowerCase().trim();
	}
	
	public String getProductTitle() {
		return productTitle;
	}
	
	public int getProductId() { return productId; }
}
