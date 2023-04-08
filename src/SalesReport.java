import java.util.ArrayList;

public class SalesReport {
    private int ReportID;
    private String ReportType; // by region/product/customer
    private ArrayList<SalesTransaction> Data;

    public SalesReport(int reportID, String reportType, ArrayList<SalesTransaction> data) {
        ReportID = reportID;
        ReportType = reportType;
        Data = data;
    }

    public int getReportID() {
        return ReportID;
    }

    public void setReportID(int reportID) {
        ReportID = reportID;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String reportType) {
        ReportType = reportType;
    }

    public ArrayList<SalesTransaction> getData() {
        return Data;
    }

    public void setData(ArrayList<SalesTransaction> data) {
        Data = data;
    }
}
