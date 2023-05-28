import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Analytics {
    private List<ProductReport> ProductReports;
    private List<RegionReport> RegionReports;
    private List<CustomerReport>  CustomerReports;

    public Analytics(List<ProductReport> productReports, List<RegionReport> regionReports, List<CustomerReport> customerReports) {
    }

    public List<ProductReport> getProductReports() {
        return ProductReports;
    }

    public void setProductReports(List<ProductReport> productReports) {
        ProductReports = productReports;
    }

    public List<RegionReport> getRegionReports() {
        return RegionReports;
    }

    public void setRegionReports(List<RegionReport> regionReports) {
        RegionReports = regionReports;
    }

    public List<CustomerReport> getCustomerReports() {
        return CustomerReports;
    }

    public void setCustomerReports(List<CustomerReport> customerReports) {
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
