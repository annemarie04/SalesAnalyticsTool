import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public MySQLAccess() throws SQLException, ClassNotFoundException {
        // This will load the MySQL driver, each DB has its own driver
        // Setup the connection with the DB
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/SalesAnalytics?" + "user=root&password=12345678");
    }

    // CREATE, READ, UPDATE, DELETE
    // Customers, Products, Region, Sales Transaction
    // **extra: Reports, Analytics

    // DONE:
    // Customers: insert_customers, delete_customer, select_customers, update_customer
    // Products: insert_products, delete_product, select_products, update_price
    // SalesReports: insert_reports, select_reports, delete_report, update_report
    // SalesTransactions: insert_transactions, delete_transaction, select_transactions, update_transactions

    // Read from DB
    public ResultSet readFromDB(String queryString) throws SQLException {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(queryString);
            return resultSet;

        } catch (Exception e) {
            throw e;
        } finally {
            //close();
        }
    }
    public void queryDataBase (String queryString) throws Exception {
        try {
                // PreparedStatement for INSERT, UPDATE, DELETE
                preparedStatement = connect.prepareStatement(queryString);
                preparedStatement.executeUpdate();

        } catch (Exception e) {
                throw e;
        }
    }
    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}

