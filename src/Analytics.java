import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Analytics {
    private ArrayList<ProductReport> ProductReports;
    private ArrayList<RegionReport> RegionReports;
    private ArrayList<CustomerReport>  CustomerReports;

    public Analytics(ArrayList<ProductReport> productReports, ArrayList<RegionReport> regionReports, ArrayList<CustomerReport> customerReports) {
    }

    public ArrayList<ProductReport> getProductReports() {
        return ProductReports;
    }

    public void setProductReports(ArrayList<ProductReport> productReports) {
        ProductReports = productReports;
    }

    public ArrayList<RegionReport> getRegionReports() {
        return RegionReports;
    }

    public void setRegionReports(ArrayList<RegionReport> regionReports) {
        RegionReports = regionReports;
    }

    public ArrayList<CustomerReport> getCustomerReports() {
        return CustomerReports;
    }

    public void setCustomerReports(ArrayList<CustomerReport> customerReports) {
        CustomerReports = customerReports;
    }

    public void sortByRevenueAndUnits() {
        Collections.sort(ProductReports, new RevenueAndUnitsComparator());
    }
    public void sortByAmountSpent() {
        Collections.sort(CustomerReports, new AmountSpentComparator());
    }
    public void sortByRevenue() {
        Collections.sort(RegionReports, new RevenueComparator());
    }
    class RevenueAndUnitsComparator implements Comparator<ProductReport> {
        @Override
        public int compare(ProductReport productReport1, ProductReport productReport2) {
            int revenueComparison = Double.compare(productReport2.getRevenueGenerated(), productReport1.getRevenueGenerated());
            if (revenueComparison != 0) {
                // If revenue is different, sort by revenue in descending order
                return revenueComparison;
            } else {
                // If revenue is the same, sort by units in ascending order
                return Integer.compare(productReport1.getUnitsSold(), productReport2.getUnitsSold());
            }
        }
    }
    class AmountSpentComparator implements Comparator<CustomerReport> {
        @Override
        public int compare(CustomerReport customerReport1, CustomerReport customerReport2) {
            int AmountSpentComparison = Double.compare(customerReport2.getAmountSpent(), customerReport1.getAmountSpent());
            return AmountSpentComparison;

        }
    }

    class RevenueComparator implements Comparator<RegionReport> {
        @Override
        public int compare(RegionReport regionReport1, RegionReport regionReport2) {
            int RevenueComparison = Double.compare(regionReport2.getRevenue(), regionReport1.getRevenue());
            return RevenueComparison;

        }
    }
}
