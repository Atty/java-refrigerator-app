package refrigerationApp.entities;

public class Product {
    private String title;

    private int id;
    private static int counter = 0;

    public Product() {
    }

    public Product(String title) {
        this.id = counter++;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId(){ return id; }
}
