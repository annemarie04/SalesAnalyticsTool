public class SalesTransaction {
    private int TransactionID;
    private Product Product;
    private Customer Customer;
    private int Quantity;
    private double TotalPrice;

    public SalesTransaction(int transactionID, Product product, Customer customer, int quantity) {
        TransactionID = transactionID;
        Product = product;
        Customer = customer;
        Quantity = quantity;
        TotalPrice = product.getPrice() * quantity;
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(int transactionID) {
        TransactionID = transactionID;
    }

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        Product = product;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer customer) {
        Customer = customer;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }
}
