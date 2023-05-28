import java.awt.event.ActionEvent;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        MySQLAccess sql = new MySQLAccess();
        Service service = new Service(sql);
        CSVService csv = new CSVService();
        Scanner MyScanner = new Scanner(System.in);
        GUI gui = new GUI(service, csv);



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
            System.out.println("9. Delete Data");
            System.out.println("10. Load from Database");
            System.out.println("11. Update Customer Information");
            System.out.println("12. Update Transaction Information");
            System.out.println("13. Add Reports to DB");
            System.out.println("14. Display Reports from DB");
            System.out.println("15. Delete Report from DB");
            System.out.println("16. Update Report Content");

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
                    csv.addRow("1.1. Add Products.");
                } else if (addType == 2) {
                    // Read customers from file
                    service.addCustomers();
                    csv.addRow("1.2. Add Customers.");
                } else if (addType == 3) {
                    // Read transactions from file
                    service.addTransactions();
                    csv.addRow("1.3. Add SalesTransactions.");
                } else if (addType == 4) {
                    // Read regions from file
                    service.addRegions();
                    csv.addRow("1.4. Add Regions.");
                } else if (addType == 5) {
                    // Read all data from file
                    service.addAll();
                    csv.addRow("1.5. Add Products, Customers, SalesTransactions, Regions.");
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
                    csv.addRow("2.1. Display Products.");
                } else if (displayType == 2) {
                    // Display all customers
                    service.displayCustomers();
                    csv.addRow("2.2. Display Customers.");
                } else if (displayType == 3) {
                    // Display transactions
                    service.displayTransactions();
                    csv.addRow("2.3. Display SalesTransactions.");
                } else if (displayType == 4) {
                    // Display regions
                     service.displayRegions();
                    csv.addRow("2.4. Display Regions.");
                } else if (displayType == 5) {
                    // Display all data
                    service.displayAll();
                    csv.addRow("2.5. Display Products, Customers, SalesTransactions, Regions.");
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
                    csv.addRow("3.1. Generate Product Report.");

                } else if (reportType == 2) {
                    // Generate Customer Report
                    System.out.println("Customer ID: ");
                    int customerId = Integer.parseInt(MyScanner.nextLine());
                    service.generateReport_byCustomer(customerId);
                    csv.addRow("3.2. Generate Customer Report.");

                } else if (reportType == 3) {
                    // Generate Region Report
                    System.out.println("Region Name: ");
                    String regionName = MyScanner.nextLine();
                    service.generateReport_byRegion(regionName);
                    csv.addRow("3.3. Generate Region Report.");

                } else {
                    System.out.println("Wrong number introduced!");
                }

            } else if (actionType == 4) {
                System.out.println("Displaying all available reports... ");
                // Display Reports
                service.displayReports();
                csv.addRow("4. Display All Reports.");


            } else if (actionType == 5) {
                System.out.println("Choose what type of Analytics to generate: ");
                System.out.println("1. Product Analytics");
                System.out.println("2. Customer Analytics");
                System.out.println("3. Region Analytics");

                int analyticsType = Integer.parseInt(MyScanner.nextLine());
                if (analyticsType == 1) {
                    // Generate Product Analytics
                    service.ProductAnalytics();
                    csv.addRow("5.1. Generate Product Analytics.");

                } else if (analyticsType == 2) {
                    // Generate Customer Analytics
                    service.CustomerAnalytics();
                    csv.addRow("5.2. Generate Customer Analytics.");

                } else if (analyticsType == 3) {
                    // Generate Region Analytics
                    service.RegionAnalytics();
                    csv.addRow("5.3. Generate Region Analytics.");

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
                csv.addRow("6. Apply Promotion to Product.");

            } else if (actionType == 7) {
                System.out.println("Enter ProductID: ");
                int prodID = Integer.parseInt(MyScanner.nextLine());
                service.displayProduct(service.getProductById(prodID));
                csv.addRow("7. Search Product by ID.");

            } else if (actionType == 8) {
                System.out.println("Enter CustomerID: ");
                int custID = Integer.parseInt(MyScanner.nextLine());
                service.displayCustomer(service.getCustomerById(custID));
                csv.addRow("8. Search Customer by ID.");

            } else if (actionType == 9){
                System.out.println("! Please read the data before deleting ! ");
                System.out.println("Choose what data to delete: ");
                System.out.println("1. Delete Product");
                System.out.println("2. Delete Customer");
                System.out.println("3. Delete Transaction");
                System.out.println("4. Delete all");

                int deleteType = Integer.parseInt(MyScanner.nextLine());
                if (deleteType == 1) {
                    System.out.println("Insert productID to delete: ");
                    //Delete product
                    int productID = Integer.parseInt(MyScanner.nextLine());
                    service.deleteProduct(productID);
                    csv.addRow("9.1. Delete Product.");

                } else if (deleteType == 2) {
                    System.out.println("Insert customerID to delete: ");
                    //Delete customer
                    int customerID = Integer.parseInt(MyScanner.nextLine());
                    service.deleteCustomer(customerID);
                    csv.addRow("9.2. Delete Customer.");

                } else if (deleteType == 3) {
                    System.out.println("Insert transactionID to delete: ");
                    //Delete transaction
                    int transactionID = Integer.parseInt(MyScanner.nextLine());
                    service.deleteTransaction(transactionID);
                    csv.addRow("9.3. Delete SalesTransaction.");

                } else if (deleteType == 4) {
                    //Delete all
                    service.deleteAll();
                    csv.addRow("9.4. Delete All Products, Customers, SalesTransactions.");

                } else {
                    System.out.println("Wrong number introduced!");
                }
            } else if (actionType == 10) {
                System.out.println("Select data to be loaded: ");
                System.out.println("1. Load Products");
                System.out.println("2. Load Customers");
                System.out.println("3. Load SalesTransactions");
                System.out.println("4. Load All");

                int dataType = Integer.parseInt(MyScanner.nextLine());
                if(dataType == 1) {
                    // Load Products from DB
                    service.selectProducts();
                    csv.addRow("10.1. Load Products from Database.");

                } else if (dataType == 2) {
                    // Load Customers from DB
                    service.selectCustomers();
                    csv.addRow("10.2. Load Customers from Database.");

                } else if (dataType == 3) {
                    // Load SalesTransactions from DB
                    service.selectTransactions();
                    csv.addRow("10.3. Load SalesTransactions from Database.");

                } else if (dataType == 4) {
                    // Load All from DB
                    service.selectProducts();
                    service.selectCustomers();
                    service.selectTransactions();
                    csv.addRow("10.4. Load Products, Customers & SalesTransactions from Database.");

                } else {
                    System.out.println("Wrong number introduced!");
                }

            } else if (actionType == 11) {
                System.out.println("Insert customerID: ");
                int customerID = Integer.parseInt(MyScanner.nextLine());
                System.out.println("New First Name: ");
                String firstName = MyScanner.nextLine();
                System.out.println("New Last Name: ");
                String lastName = MyScanner.nextLine();
                service.updateCustomerInfo(customerID, firstName, lastName);
                csv.addRow("11. Update Customer Information in Database.");

            } else if(actionType == 12) {
                System.out.println("Insert TransactionID: ");
                int transactionID = Integer.parseInt(MyScanner.nextLine());
                System.out.println("New Product ID: ");
                int productID = Integer.parseInt(MyScanner.nextLine());
                System.out.println("New CustomerID: ");
                int customerID = Integer.parseInt(MyScanner.nextLine());
                System.out.println("New Quantity: ");
                int quantity = Integer.parseInt(MyScanner.nextLine());
                service.updateTransactionInfo(transactionID, productID, customerID, quantity);
                csv.addRow("12. Update SalesTransaction Information in Database.");


            } else if (actionType == 13) {
                service.addReportsToDB();
                System.out.println("Reports successfully added to the Database!");
                csv.addRow("13. Add all Reports to Database.");


            } else if (actionType == 14) {
                System.out.println("The following reports have been saved to the Database: \n");
                service.selectReportsFromDB();
                csv.addRow("14. Display all Reports saved to Database.");

            } else if (actionType == 15) {
                System.out.println("Insert Report ID to be deleted: ");
                int reportID = Integer.parseInt(MyScanner.nextLine());
                service.deleteReportById(reportID);
                System.out.println("Report deleted successfully!");
                csv.addRow("15. Delete Report from Database.");

            } else if (actionType == 16) {
                System.out.println("Insert Report ID to be updated: ");
                int reportID = Integer.parseInt(MyScanner.nextLine());
                System.out.println("New Content: ");
                String reportContent = MyScanner.nextLine();
                service.updateReportById(reportID, reportContent);
                csv.addRow("16. Update Report Content in Database.");
            } else {
                System.out.println("Wrong number introduced!");
            }
        }
    }
}