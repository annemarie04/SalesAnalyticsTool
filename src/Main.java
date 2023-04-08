import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        Scanner MyScanner = new Scanner(System.in);
        while(true) {
            System.out.println();
            System.out.println("Choose an action: ");
            System.out.println("1. Add Data");
            System.out.println("2. Display Data");
            System.out.println("3. Generate Reports");
            System.out.println("4. Display Reports");
            System.out.println("5. Generate Analytics");
            System.out.println("6. Apply Promotion");
            System.out.println("7. Search Product");
            System.out.println("8. Search Customer");
            int actionType = Integer.parseInt(MyScanner.nextLine());

            if(actionType == 1) {
                System.out.println("Choose what data to add: ");
                System.out.println("1. Add Products");
                System.out.println("2. Add Customers");
                System.out.println("3. Add Transactions");
                System.out.println("4. Add Regions");
                System.out.println("5. Add all");

                int addType = Integer.parseInt(MyScanner.nextLine());
                if (addType == 1) {
                    // Read products from file
                    service.addProducts();
                } else if (addType == 2) {
                    // Read customers from file
                    service.addCustomers();
                } else if (addType == 3) {
                    // Read transactions from file
                    service.addTransactions();
                } else if (addType == 4) {
                    // Read regions from file
                    service.addRegions();
                } else if (addType == 5) {
                    // Read all data from file
                    service.addAll();
                } else {
                    System.out.println("Wrong number introduced!");
                }

            } else if (actionType == 2) {
                System.out.println("! Please read the data before displaying ! ");
                System.out.println("Choose what data to display: ");
                System.out.println("1. Display Products");
                System.out.println("2. Display Customers");
                System.out.println("3. Display Transactions");
                System.out.println("4. Display Regions");
                System.out.println("5. Display all");

                int displayType = Integer.parseInt(MyScanner.nextLine());
                if (displayType == 1) {
                    //Display all products
                    service.displayProducts();
                } else if (displayType == 2) {
                    // Display all customers
                    service.displayCustomers();
                } else if (displayType == 3) {
                    // Display transactions
                    service.displayTransactions();
                } else if (displayType == 4) {
                    // Display regions
                     service.displayRegions();
                } else if (displayType == 5) {
                    // Display all data
                    service.displayAll();
                } else {
                    System.out.println("Wrong number introduced!");
                }

            } else if (actionType == 3) {
                System.out.println("Choose what type of Report to generate: ");
                System.out.println("1. Report By Product");
                System.out.println("2. Report by Customer");
                System.out.println("3. Reports by Region");

                int reportType = Integer.parseInt(MyScanner.nextLine());
                if (reportType == 1) {
                    // Generate Product Report
                    System.out.println("Product Name: ");
                    String productName = MyScanner.nextLine();
                    service.generateReport_byProduct(productName);
                } else if (reportType == 2) {
                    // Generate Customer Report
                    System.out.println("Customer ID: ");
                    int customerId = Integer.parseInt(MyScanner.nextLine());
                    service.generateReport_byCustomer(customerId);

                } else if (reportType == 3) {
                    // Generate Region Report
                    System.out.println("Region Name: ");
                    String regionName = MyScanner.nextLine();
                    service.generateReport_byRegion(regionName);

                } else {
                    System.out.println("Wrong number introduced!");
                }

            } else if (actionType == 4) {
                System.out.println("Displaying all available reports... ");
                // Display Reports
                service.displayReports();

            } else if (actionType == 5) {
                System.out.println("Choose what type of Analytics to generate: ");
                System.out.println("1. Product Analytics");
                System.out.println("2. Customer Analytics");
                System.out.println("3. Region Analytics");

                int analyticsType = Integer.parseInt(MyScanner.nextLine());
                if (analyticsType == 1) {
                    // Generate Product Analytics
                    service.ProductAnalytics();
                } else if (analyticsType == 2) {
                    // Generate Customer Analytics
                    service.CustomerAnalytics();
                } else if (analyticsType == 3) {
                    // Generate Region Analytics
                    service.RegionAnalytics();
                } else {
                    System.out.println("Wrong number introduced!");
                }

            } else if (actionType == 6) {
                System.out.println("Select productID to apply promotion: ");
                int prodID = Integer.parseInt(MyScanner.nextLine());
                System.out.println("Promotion percentage: ");
                int percentage = Integer.parseInt(MyScanner.nextLine());
                // Apply Promotion on Product
                service.applyPromotion(prodID, percentage);
            } else if (actionType == 7) {
                System.out.println("Enter ProductID: ");
                int prodID = Integer.parseInt(MyScanner.nextLine());
                service.displayProduct(service.getProductById(prodID));

            } else if (actionType == 8) {
                System.out.println("Enter CustomerID: ");
                int custID = Integer.parseInt(MyScanner.nextLine());
                service.displayCustomer(service.getCustomerById(custID));
            } else {
                System.out.println("Wrong number introduced!");
            }
        }
    }
}