import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Service {
    List<Product> products = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    List<SalesTransaction> transactions = new ArrayList<>();
    List<Region> regions = new ArrayList<>();
    List<SalesReport> reports = new ArrayList<>();

    List<ProductReport> productReports = new ArrayList<>();
    List<RegionReport> regionReports = new ArrayList<>();
    List<CustomerReport> customerReports = new ArrayList<>();
    Analytics analytics = new Analytics(productReports, regionReports, customerReports);
    MySQLAccess sql = new MySQLAccess();

    public Service(MySQLAccess sql) throws SQLException, ClassNotFoundException {
        this.sql = sql;
    }

    public void addAll() {
        addProducts();
        addCustomers();
        addTransactions();
        addRegions();
    }
    public void displayAll() {
        displayProducts();
        displayReports();
        displayTransactions();
        displayRegions();
    }
    public void deleteAll() throws Exception {
        String queryProducts = "delete from Products";
        sql.queryDataBase(queryProducts);

        String queryCustomers = "delete from Customers";
        sql.queryDataBase(queryCustomers);

        String queryTransactions = "delete from SalesTransactions";
        sql.queryDataBase(queryTransactions);

        String queryAlterProducts = "ALTER TABLE Products AUTO_INCREMENT = 1;";
        sql.queryDataBase(queryAlterProducts);

        String queryAlterCustomers = "ALTER TABLE Customers AUTO_INCREMENT = 1;";
        sql.queryDataBase(queryAlterCustomers);

        String queryAlterTransactions = "ALTER TABLE SalesTransactions AUTO_INCREMENT = 1;";
        sql.queryDataBase(queryAlterTransactions);
    }
    // ------------------------------------------- PRODUCTS ----------------------------------------------------
    public void addProducts() {
        try {
            File myProducts = new File("products.txt");
            Scanner myReader = new Scanner(myProducts);
            int id = 1;
            while (myReader.hasNextLine()) {
                String name = myReader.nextLine();
                String category = myReader.nextLine();
                Double price = Double.parseDouble(myReader.nextLine());
                String description = myReader.nextLine();

                Product p = new Product (id, name, category, price, description);
                products.add(p);
                String queryString = String.format("insert into Products values (default, '%s', '%s', %.2f, '%s' )", name, category, price, description);
                sql.queryDataBase(queryString);
                id ++;
            }
            System.out.println("Products added successfully!");

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Products: An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void displayProducts() {
        for(int i = 0 ; i < products.size(); i ++) {
            System.out.println("ID: " + products.get(i).getProductID());
            System.out.println("Name: " + products.get(i).getProductName());
            System.out.println("Category: " + products.get(i).getCategory());
            System.out.println("Price: " + products.get(i).getPrice());
            System.out.println("Description: " + products.get(i).getDescription());
            System.out.println();
        }
    }
    public void displayProduct(Product product) {
        System.out.println("Product ID: " + product.getProductID());
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Category: " + product.getCategory());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Description: " + product.getDescription());
    }
    public Product getProductById(int id) {
        for(int i = 0; i < products.size(); i ++) {
            if(products.get(i).getProductID() == id)
                return products.get(i);
        }
        return null;
    }
    public void applyPromotion(int productID, int percentage) throws Exception {
        for(Product product: products) {
            if(product.getProductID() == productID) {
                Double newPrice = product.getPrice() - product.getPrice() * percentage / 100;
                product.setPrice(newPrice);
                System.out.println("Promotion applied successfully!");
                 String queryString = String.format("update products set price = %.2f where id_product = %d", newPrice, productID);
                sql.queryDataBase(queryString);
                return;
            }
        }
        System.out.println("Applying the promotion failed.");
    }
    public void deleteProduct(int productID) throws Exception {
        String queryString = String.format("delete from Products where id_product = %d", productID);
        System.out.println(queryString);
        sql.queryDataBase(queryString);
    }
    public void selectProducts() throws SQLException {
        ResultSet resultSet = sql.readFromDB("select * from Products");

        // ResultSet is initially before the first data set
        while (resultSet.next()) {

            int product_id = resultSet.getInt("id_product");
            String productName = resultSet.getString("product_name");
            String category = resultSet.getString("category");
            Double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            Product p = new Product(product_id, productName, category, price, description);
            products.add(p);
        }
    }
    // ------------------------------------------- CUSTOMERS ----------------------------------------------------
    public void addCustomers() {
        try {
            File myCustomers = new File("customers.txt");
            Scanner myReader = new Scanner(myCustomers);
            int id = 1;
            while (myReader.hasNextLine()) {
                String firstName = myReader.nextLine();
                String lastName = myReader.nextLine();

                Customer c = new Customer (id, firstName, lastName);
                customers.add(c);
                String queryString = String.format("insert into  Customers values (default, '%s', '%s')", firstName, lastName);
                sql.queryDataBase(queryString);

                id ++;
            }
            System.out.println("Customers added successfully!");
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Customers: An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void displayCustomers() {
        for(int i = 0 ; i < customers.size(); i ++) {
            System.out.println("ID: " + customers.get(i).getCustomerID());
            System.out.println("First Name: " + customers.get(i).getFirstName());
            System.out.println("Last Name: " + customers.get(i).getLastName());

            System.out.println();
        }
    }
    public void displayCustomer(Customer customer) {
        System.out.println("Customer ID: " + customer.getCustomerID());
        System.out.println("First Name: " + customer.getFirstName());
        System.out.println("Last Name: " + customer.getLastName());
    }
    public Customer getCustomerById(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID() == id)
                return customers.get(i);
        }
        return null;
    }
    public void deleteCustomer(int customerID) throws Exception {
        String queryString = String.format("delete from Customers where id_customer = %d", customerID);
        System.out.println(queryString);
        sql.queryDataBase(queryString);
    }
    public void selectCustomers() throws SQLException {
        ResultSet resultSet = sql.readFromDB("select * from Customers");

        // ResultSet is initially before the first data set
        while (resultSet.next()) {

            int customer_id = resultSet.getInt("id_customer");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Customer c = new Customer(customer_id, firstName, lastName);
            customers.add(c);
        }
    }
    public void updateCustomerInfo(int customerID, String firstName, String lastName) throws Exception {
        String queryString = String.format("update Customers set first_name = '%s', last_name = '%s' where id_customer = %d", firstName, lastName, customerID);
        sql.queryDataBase(queryString);
    }
    // ------------------------------------------- TRANSACTIONS ----------------------------------------------------
    public void addTransactions() {
        try {
            File myTransactions = new File("transactions.txt");
            Scanner myReader = new Scanner(myTransactions);
            int id = 1;
            while(myReader.hasNextLine()) {
                int idCustomer = myReader.nextInt();
                Customer c = getCustomerById(idCustomer);
                int idProduct = myReader.nextInt();
                Product p = getProductById(idProduct);
                int quantity = myReader.nextInt();
                if(c == null)
                    System.out.println("Customer not found.");
                else if (p == null) {
                    System.out.println("Product not found.");
                } else {
                    SalesTransaction st = new SalesTransaction(id, p, c, quantity);
                    transactions.add(st);
                    String queryString = String.format("insert into SalesTransactions values (default, %d, %d, %d, %.2f)", idCustomer, idProduct, quantity, st.getTotalPrice());
                    System.out.println(queryString);
                    sql.queryDataBase(queryString);
                    id ++;
                }
            }

            System.out.println("Transactions added successfully!");
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Transactions: An error occured.");
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void displayTransactions() {
        for(int i = 0; i < transactions.size(); i ++) {
            System.out.println("ID: " + transactions.get(i).getTransactionID());
            System.out.println("Product: " + transactions.get(i).getProduct().getProductName());
            System.out.println("Customer: " + transactions.get(i).getCustomer().getFirstName() + " " + transactions.get(i).getCustomer().getLastName());
            System.out.println("Quantity: " + transactions.get(i).getQuantity());
            System.out.println("Total Price: " + transactions.get(i).getTotalPrice());

            System.out.println();
        }
    }
    public SalesTransaction getTransactionById(int id) {
        for(int i = 0; i < transactions.size(); i ++) {
            if(transactions.get(i).getTransactionID() == id)
                return transactions.get(i);
        }
        return null;
    }
    public void deleteTransaction(int transactionID) throws Exception {
        String queryString = String.format("delete from SalesTransactions where id_transaction = %d", transactionID);
        System.out.println(queryString);
        sql.queryDataBase(queryString);
    }
    public void selectTransactions() throws SQLException {
        ResultSet resultSet = sql.readFromDB("select * from SalesTransactions");

        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            int transaction_id = resultSet.getInt("id_transaction");
            int product_id = resultSet.getInt("id_product");
            int customer_id = resultSet.getInt("id_customer");
            int quantity = resultSet.getInt("quantity");
            Double totalPrice = resultSet.getDouble("total_price");
            SalesTransaction t = new SalesTransaction(transaction_id, getProductById(product_id), getCustomerById(customer_id), quantity);
            transactions.add(t);
        }
    }
    public void updateTransactionInfo(int transactionID, int productID, int customerID, int quantity) throws Exception {
        String queryPrice = String.format("select price from Products where id_product = %d", productID);
        ResultSet resultSet = sql.readFromDB(queryPrice);
        Double price = 0.0;
        while(resultSet.next()){
            price = resultSet.getDouble("price");
        }

        Double totalPrice = price * quantity;
        String queryString = String.format("update SalesTransactions set id_product = %d, id_customer = %d, quantity = %d, total_price = %.2f where id_transaction = %d", productID, customerID, quantity, totalPrice, transactionID );
        sql.queryDataBase(queryString);
    }
    // ------------------------------------------- REGIONS ----------------------------------------------------
    public void addRegions() {
        try {
            File myRegions = new File("regions.txt");
            Scanner myReader = new Scanner(myRegions);
            int id = 1;
            while(myReader.hasNextLine()) {
                String name = myReader.nextLine();
                int idT1 = Integer.parseInt(myReader.nextLine());
                int idT2 = Integer.parseInt(myReader.nextLine());
                int idT3 = Integer.parseInt(myReader.nextLine());

                SalesTransaction transaction1 = getTransactionById(idT1);
                SalesTransaction transaction2 = getTransactionById(idT2);
                SalesTransaction transaction3 = getTransactionById(idT3);
                if(transaction1 != null & transaction2 != null & transaction3 != null) {
                    ArrayList<SalesTransaction> transactions = new ArrayList<SalesTransaction>();
                    transactions.add(transaction1);
                    transactions.add(transaction2);
                    transactions.add(transaction3);
                    Region r = new Region(id, name, transactions);
                    regions.add(r);
                    id ++;
                }
                else {
                    System.out.println("Transactions not found.");
                }
            }
        System.out.println("Regions added successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Regions: An error occured.");
            e.printStackTrace();
        }
    }
    public void displayRegions() {
        for(int i = 0; i < regions.size(); i ++) {
            System.out.println("ID: " + regions.get(i).getRegionID());
            System.out.println("Name: " + regions.get(i).getName());
            System.out.println("Transactions: " + regions.get(i).getTransactions());
            System.out.println();
        }
    }
    // ------------------------------------------- REPORTS ----------------------------------------------------
    public void generateReport_byProduct(String productName) {
        int id = reports.size() + 1;
        int productID = -1;
        ArrayList<SalesTransaction> transList = new ArrayList<>();
        for(Product product: products) {
            if(product.getProductName().toLowerCase().equals(productName.toLowerCase())) {
                productID = product.getProductID();
            }
        }
        if(productID != -1) {
            double sumSales = 0;
            int unitsSold = 0;
            Product p = null;
            for(SalesTransaction trans: transactions) {
                if(trans.getProduct().getProductID() == productID) {
                    transList.add(trans);
                    unitsSold += trans.getQuantity();
                    sumSales += trans.getTotalPrice();
                    p = trans.getProduct();
                }
            }
            if(p != null) {
                ProductReport report = new ProductReport(id, "byProduct", transList, p, sumSales, unitsSold);
                productReports.add(report);
                analytics.setProductReports(productReports);
                reports.add(report);
                System.out.println("Product report generated successfully!");
            }
        } else {
            System.out.println("Product not found.");
        }
    }
    public void generateReport_byRegion(String regionName) {
        int id = reports.size() + 1;
        int regionID = -1;
        Region r = null;
        ArrayList<SalesTransaction> transactions = new ArrayList<>();
        for(Region region: regions) {
            if(region.getName().toLowerCase().equals(regionName.toLowerCase())) {
                regionID = region.getRegionID();
                transactions = region.getTransactions();
                r = region;
            }
        }
        if(regionID != -1) {
            double sumSales = 0;
            HashMap<Product, Double> productRevenueMap = new HashMap<Product, Double>();
            for(SalesTransaction trans: transactions) {
                Product product = trans.getProduct();
                double totalRevenue = trans.getTotalPrice();
                sumSales += totalRevenue;

                if (productRevenueMap.containsKey(product)) {
                    double currentRevenue = productRevenueMap.get(product);
                    productRevenueMap.put(product, currentRevenue + totalRevenue);
                } else {
                    productRevenueMap.put(product, totalRevenue);
                }
            }

            Map.Entry<Product, Double> maxRevenueEntry = null;
            for (Map.Entry<Product, Double> entry : productRevenueMap.entrySet()) {
                if (maxRevenueEntry == null || entry.getValue() > maxRevenueEntry.getValue()) {
                    maxRevenueEntry = entry;
                }
            }
            RegionReport report = new RegionReport(id, "byRegion",transactions, r, maxRevenueEntry.getKey(), sumSales);
            regionReports.add(report);
            analytics.setRegionReports(regionReports);
            reports.add(report);
            System.out.println("Region report generated successfully!");
        } else {
            System.out.println("Region not found.");
        }
    }
    public void generateReport_byCustomer(int customerID) {
        int id = reports.size() + 1;
        ArrayList<SalesTransaction> transList = new ArrayList<>();
        Customer customer = getCustomerById(customerID);
        HashMap<Product, Integer> customerProductMap = new HashMap<Product, Integer>();

        double totalSpent = 0;
        for (SalesTransaction trans : transactions) {
            if (trans.getCustomer().getCustomerID() == customerID) {
                transList.add(trans);
                totalSpent += trans.getTotalPrice();
                Product product = trans.getProduct();
                int quantity = trans.getQuantity();

                if (customerProductMap.containsKey(product)) {
                    int currentQuantity = customerProductMap.get(product);
                    customerProductMap.put(product, currentQuantity + quantity);
                } else {
                    customerProductMap.put(product, quantity);
                }
            }
        }
        Map.Entry<Product, Integer> maxQuantity = null;
        for (Map.Entry<Product, Integer> entry : customerProductMap.entrySet()) {
            if (maxQuantity == null || entry.getValue() > maxQuantity.getValue()) {
                maxQuantity = entry;
            }
        }
        Product favProduct = maxQuantity.getKey();
        CustomerReport report = new CustomerReport(id, "byCustomer", transList, customer, favProduct, totalSpent);
        customerReports.add(report);
        analytics.setCustomerReports(customerReports);
        reports.add(report);
        System.out.println("Customer report generated successfully!");
    }
    public void displayReports() {
        for(int i = 0; i < reports.size(); i ++){
            System.out.println("Report #" + reports.get(i).getReportID() + " " + reports.get(i).getReportType());
            System.out.println("Transactions: " + reports.get(i).getData().size());
            if(reports.get(i) instanceof ProductReport) {
                ProductReport report = (ProductReport) reports.get(i);
                report.DisplayReport();
            } else if (reports.get(i) instanceof RegionReport) {
                RegionReport report = (RegionReport) reports.get(i);
                report.DisplayReport();
            } else if (reports.get(i) instanceof CustomerReport) {
                CustomerReport report = (CustomerReport) reports.get(i);
                report.DisplayReport();
            }
        }
    }
    public void addReportsToDB() throws Exception {
        for(int i = 0; i < reports.size(); i ++){
            int reportID = reports.get(i).getReportID();
            String reportType = reports.get(i).getReportType();
            int numTransactions = reports.get(i).getData().size();
            String reportContent = String.format("Transactions: %d\n", numTransactions);
            if(reports.get(i) instanceof ProductReport) {
                ProductReport report = (ProductReport) reports.get(i);
                reportContent += report.generateReportContent();
            } else if (reports.get(i) instanceof RegionReport) {
                RegionReport report = (RegionReport) reports.get(i);
                reportContent += report.generateReportContent();
            } else if (reports.get(i) instanceof CustomerReport) {
                CustomerReport report = (CustomerReport) reports.get(i);
                reportContent += report.generateReportContent();
            }
            String queryString = String.format("insert into SalesReports value (default, '%s', '%s')", reportType, reportContent);
            sql.queryDataBase(queryString);
        }
    }
    public void selectReportsFromDB() throws SQLException {
        String queryString = "select * from SalesReports";
        ResultSet resultSet = sql.readFromDB(queryString);

        while(resultSet.next()) {
            int reportID = resultSet.getInt("id_report");
            String reportType = resultSet.getString("type");
            String reportContent = resultSet.getString("content");

            System.out.println("Report #" + Integer.toString(reportID) + " " + reportType);
            System.out.println(reportContent + '\n');
        }
    }
    public void deleteReportById(int reportID) throws Exception {
        String queryString = String.format("delete from SalesReports where id_report = %d", reportID);
        sql.queryDataBase(queryString);
    }
    public void updateReportById(int reportID, String content) throws Exception {
        String queryString = String.format("update SalesReports set content = '%s' where id_report = %d", content, reportID);
        sql.queryDataBase(queryString);
    }
    // ------------------------------------------- ANALYTICS ----------------------------------------------------
    public void ProductAnalytics() {
        // Generate Reports for all Products
        for(Product p : products) {
            generateReport_byProduct(p.getProductName());
        }
        // Generate Analytics for ProductReports
        analytics.sortByRevenueAndUnits();
        // Display ProductAnalytics
        for(ProductReport rep : analytics.getProductReports()) {
            rep.DisplayReport();
        }
    }
    public void CustomerAnalytics() {
        // Generate Reports for all Customers
        for(Customer c : customers) {
            generateReport_byCustomer(c.getCustomerID());
        }
        // Generate Analytics for CustomerReports
        analytics.sortByAmountSpent();
        // Display CustomerAnalytics
        for(CustomerReport rep : analytics.getCustomerReports()) {
            rep.DisplayReport();
        }
    }
    public void RegionAnalytics() {
        // Generate Reports for all Customers
        for(Region r : regions) {
            generateReport_byRegion(r.getName());
        }
        // Generate Analytics for CustomerReports
        analytics.sortByRevenue();
        // Display CustomerAnalytics
        for(RegionReport rep : analytics.getRegionReports()) {
            rep.DisplayReport();
        }
    }
}
