import java.util.ArrayList;

public class CustomerReport extends SalesReport{
    private Customer Customer;
    private Product FavProduct;
    private double AmountSpent;

    public CustomerReport(int reportID, String reportType, ArrayList<SalesTransaction> data, Customer customer, Product favProduct, double amountSpent) {
        super(reportID, reportType, data);
        Customer = customer;
        FavProduct = favProduct;
        AmountSpent = amountSpent;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer customer) {
        Customer = customer;
    }

    public Product getFavProduct() {
        return FavProduct;
    }

    public void setFavProduct(Product favProduct) {
        FavProduct = favProduct;
    }

    public double getAmountSpent() {
        return AmountSpent;
    }

    public void setAmountSpent(double amountSpent) {
        AmountSpent = amountSpent;
    }

    public void DisplayReport() {
        System.out.println("Customer: " + Customer.getFirstName() + " " + Customer.getLastName());
        System.out.println("Favourite Product: " + FavProduct.getProductName());
        System.out.println("Amount Spent: " + AmountSpent);
        System.out.println();
    }
}
