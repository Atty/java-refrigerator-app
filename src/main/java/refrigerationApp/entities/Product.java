package refrigerationApp.entities;

public class Product {
    private String productTitle;

    private int productId;
    private static int counter = 0;

    public Product() {
    }

    public Product(String title) {
        this.productId = counter++;
        this.productTitle = title;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getProductId(){ return productId; }
}
