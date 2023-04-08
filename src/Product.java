public class Product {
    private int ProductID;
    private String ProductName;
    private String Category;
    private Double Price;
    private String Description;

    public Product(int productID, String productName, String category, Double price, String description) {
        ProductID = productID;
        ProductName = productName;
        Category = category;
        Price = price;
        Description = description;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
