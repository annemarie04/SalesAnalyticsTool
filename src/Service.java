import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class Service {
    ArrayList<Product> Products = new ArrayList<>();
    ArrayList<Customer> Customers = new ArrayList<>();
    ArrayList<SalesTransaction> Transactions = new ArrayList<>();
    ArrayList<Region> Regions = new ArrayList<>();
    ArrayList<SalesReport> Reports = new ArrayList<>();

    ArrayList<ProductReport> ProductReports = new ArrayList<>();
    ArrayList<RegionReport> RegionReports = new ArrayList<>();
    ArrayList<CustomerReport> CustomerReports = new ArrayList<>();
    Analytics analytics = new Analytics(ProductReports, RegionReports, CustomerReports);

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
                Products.add(p);
                id ++;
            }
            System.out.println("Products added successfully!");

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Products: An error occurred.");
            e.printStackTrace();
        }
    }
    public void displayProducts() {
        for(int i = 0 ; i < Products.size(); i ++) {
            System.out.println("ID: " + Products.get(i).getProductID());
            System.out.println("Name: " + Products.get(i).getProductName());
            System.out.println("Category: " + Products.get(i).getCategory());
            System.out.println("Price: " + Products.get(i).getPrice());
            System.out.println("Description: " + Products.get(i).getDescription());
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
        for(int i = 0; i < Products.size(); i ++) {
            if(Products.get(i).getProductID() == id)
                return Products.get(i);
        }
        return null;
    }
    public void applyPromotion(int productID, int percentage) {
        for(Product product: Products) {
            if(product.getProductID() == productID) {
                Double newPrice = product.getPrice() - product.getPrice() * percentage / 100;
                product.setPrice(newPrice);
                System.out.println("Promotion applied successfully!");
                return;
            }
        }
        System.out.println("Applying the promotion failed.");
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
                Customers.add(c);
                id ++;
            }
            System.out.println("Customers added successfully!");
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Customers: An error occurred.");
            e.printStackTrace();
        }
    }
    public void displayCustomers() {
        for(int i = 0 ; i < Customers.size(); i ++) {
            System.out.println("ID: " + Customers.get(i).getCustomerID());
            System.out.println("First Name: " + Customers.get(i).getFirstName());
            System.out.println("Last Name: " + Customers.get(i).getLastName());

            System.out.println();
        }
    }
    public void displayCustomer(Customer customer) {
        System.out.println("Customer ID: " + customer.getCustomerID());
        System.out.println("First Name: " + customer.getFirstName());
        System.out.println("Last Name: " + customer.getLastName());
    }
    public Customer getCustomerById(int id) {
        for (int i = 0; i < Customers.size(); i++) {
            if (Customers.get(i).getCustomerID() == id)
                return Customers.get(i);
        }
        return null;
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
                    Transactions.add(st);
                    id ++;
                }
            }

            System.out.println("Transactions added successfully!");
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Transactions: An error occured.");
            e.printStackTrace();
        }
    }
    public void displayTransactions() {
        for(int i = 0; i < Transactions.size(); i ++) {
            System.out.println("ID: " + Transactions.get(i).getTransactionID());
            System.out.println("Product: " + Transactions.get(i).getProduct().getProductName());
            System.out.println("Customer: " + Transactions.get(i).getCustomer().getFirstName() + " " + Transactions.get(i).getCustomer().getLastName());
            System.out.println("Quantity: " + Transactions.get(i).getQuantity());
            System.out.println("Total Price: " + Transactions.get(i).getTotalPrice());

            System.out.println();
        }
    }
    public SalesTransaction getTransactionById(int id) {
        for(int i = 0; i < Transactions.size(); i ++) {
            if(Transactions.get(i).getTransactionID() == id)
                return Transactions.get(i);
        }
        return null;
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
                int idT2 = Integer.parseInt(myReader.nextLine());;
                int idT3 = Integer.parseInt(myReader.nextLine());;

                SalesTransaction transaction1 = getTransactionById(idT1);
                SalesTransaction transaction2 = getTransactionById(idT2);
                SalesTransaction transaction3 = getTransactionById(idT3);
                if(transaction1 != null & transaction2 != null & transaction3 != null) {
                    ArrayList<SalesTransaction> transactions = new ArrayList<SalesTransaction>();
                    transactions.add(transaction1);
                    transactions.add(transaction2);
                    transactions.add(transaction3);
                    Region r = new Region(id, name, transactions);
                    Regions.add(r);
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
        for(int i = 0; i < Regions.size(); i ++) {
            System.out.println("ID: " + Regions.get(i).getRegionID());
            System.out.println("Name: " + Regions.get(i).getName());
            System.out.println("Transactions: " + Regions.get(i).getTransactions());
            System.out.println();
        }
    }

    // ------------------------------------------- REPORTS ----------------------------------------------------
    public void generateReport_byProduct(String productName) {
        int id = Reports.size() + 1;
        int productID = -1;
        ArrayList<SalesTransaction> transList = new ArrayList<>();
        for(Product product: Products) {
            if(product.getProductName().toLowerCase().equals(productName.toLowerCase())) {
                productID = product.getProductID();
            }
        }
        if(productID != -1) {
            double sumSales = 0;
            int unitsSold = 0;
            Product p = null;
            for(SalesTransaction trans: Transactions) {
                if(trans.getProduct().getProductID() == productID) {
                    transList.add(trans);
                    unitsSold += trans.getQuantity();
                    sumSales += trans.getTotalPrice();
                    p = trans.getProduct();
                }
            }
            if(p != null) {
                ProductReport report = new ProductReport(id, "byProduct", transList, p, sumSales, unitsSold);
                ProductReports.add(report);
                analytics.setProductReports(ProductReports);
                Reports.add(report);
                System.out.println("Product report generated successfully!");
            }
        } else {
            System.out.println("Product not found.");
        }
    }
    public void generateReport_byRegion(String regionName) {
        int id = Reports.size() + 1;
        int regionID = -1;
        Region r = null;
        ArrayList<SalesTransaction> transactions = new ArrayList<>();
        for(Region region: Regions) {
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
            RegionReports.add(report);
            analytics.setRegionReports(RegionReports);
            Reports.add(report);
            System.out.println("Region report generated successfully!");
        } else {
            System.out.println("Region not found.");
        }
    }
    public void generateReport_byCustomer(int customerID) {
        int id = Reports.size() + 1;
        ArrayList<SalesTransaction> transList = new ArrayList<>();
        Customer customer = getCustomerById(customerID);
        HashMap<Product, Integer> customerProductMap = new HashMap<Product, Integer>();

        double totalSpent = 0;
        for (SalesTransaction trans : Transactions) {
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
        CustomerReports.add(report);
        analytics.setCustomerReports(CustomerReports);
        Reports.add(report);
        System.out.println("Customer report generated successfully!");
    }
    public void displayReports() {
        for(int i = 0; i < Reports.size(); i ++){
            System.out.println("Report #" + Reports.get(i).getReportID() + " " + Reports.get(i).getReportType());
            System.out.println("Transactions: " + Reports.get(i).getData().size());
            if(Reports.get(i) instanceof ProductReport) {
                ProductReport report = (ProductReport) Reports.get(i);
                report.DisplayReport();
            } else if (Reports.get(i) instanceof RegionReport) {
                RegionReport report = (RegionReport) Reports.get(i);
                report.DisplayReport();
            } else if (Reports.get(i) instanceof CustomerReport) {
                CustomerReport report = (CustomerReport) Reports.get(i);
                report.DisplayReport();
            }
        }
    }

    // ------------------------------------------- ANALYTICS ----------------------------------------------------
    public void ProductAnalytics() {
        // Generate Reports for all Products
        for(Product p : Products) {
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
        for(Customer c : Customers) {
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
        for(Region r : Regions) {
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


// analytics
// -> multiple products reports: most generated income, most units sold
// -> multiple regions: best income generating region, region with most products sold
// -> per customer: prefered product; moste expensive product bought; most produdct bought

// promotion: reducere cu p% a unui produs