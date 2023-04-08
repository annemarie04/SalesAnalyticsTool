import java.util.ArrayList;

public class RegionReport extends SalesReport{
    private Region Region;
    private Product BestProduct;
    private double Revenue;


    public RegionReport(int reportID, String reportType, ArrayList<SalesTransaction> data, Region region, Product bestProduct, double revenue) {
        super(reportID, reportType, data);
        Region = region;
        BestProduct = bestProduct;
        Revenue = revenue;
    }

    public Region getRegion() {
        return Region;
    }

    public void setRegion(Region region) {
        Region = region;
    }

    public Product getBestProduct() {
        return BestProduct;
    }

    public void setBestProduct(Product bestProduct) {
        BestProduct = bestProduct;
    }

    public double getRevenue() {
        return Revenue;
    }

    public void setRevenue(double revenue) {
        Revenue = revenue;
    }

    public void DisplayReport() {
        System.out.println("Region: " + Region.getName());
        System.out.println("Best Product: " + BestProduct.getProductName());
        System.out.println("Total Revenue: " + Revenue);
        System.out.println();
    }
}
