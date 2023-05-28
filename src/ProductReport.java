import java.util.ArrayList;

public class ProductReport extends SalesReport{
    private Product Product;
    private double RevenueGenerated;
    private int UnitsSold;

    public ProductReport(int reportID, String reportType, ArrayList<SalesTransaction> data, Product product, double revenueGenerated, int unitsSold) {
        super(reportID, reportType, data);
        Product = product;
        RevenueGenerated = revenueGenerated;
        UnitsSold = unitsSold;
    }

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        Product = product;
    }

    public double getRevenueGenerated() {
        return RevenueGenerated;
    }

    public void setRevenueGenerated(double revenueGenerated) {
        RevenueGenerated = revenueGenerated;
    }

    public int getUnitsSold() {
        return UnitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        UnitsSold = unitsSold;
    }

    public void DisplayReport() {
        System.out.println("Product: " + Product.getProductName());
        System.out.println("Revenue Generated: " + RevenueGenerated);
        System.out.println("Units Sold: " + UnitsSold);
        System.out.println();
    }

    public String generateReportContent() {
        String productName = Product.getProductName();
        Double revenueGenerated = RevenueGenerated;
        int unitsSold = UnitsSold;
        String reportContent = String.format("Product: %s\nRevenue Generated: %.2f\nUnits Sold: %d\n", productName, revenueGenerated, unitsSold);
        return reportContent;
    }
}
