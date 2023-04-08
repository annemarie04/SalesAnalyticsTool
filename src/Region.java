import java.util.ArrayList;

public class Region {
    private int RegionID;
    private String Name;
    private ArrayList<SalesTransaction> Transactions;

    public Region(int regionID, String name, ArrayList<SalesTransaction> transactions) {
        RegionID = regionID;
        Name = name;
        Transactions = transactions;
    }

    public int getRegionID() {
        return RegionID;
    }

    public void setRegionID(int regionID) {
        RegionID = regionID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<SalesTransaction> getTransactions() {
        return Transactions;
    }

    public void setTransactions(ArrayList<SalesTransaction> transactions) {
        Transactions = transactions;
    }
}
