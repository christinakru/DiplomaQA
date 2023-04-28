package data;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");
    private static Connection connection;

    private static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void cleanDb() {
        var runner = new QueryRunner();
        var creditRequest = "DELETE FROM credit_request_entity";
        var order = "DELETE FROM order_entity";
        var payment = "DELETE FROM payment_entity";

        try (var conn = getConnection()) {
            runner.update(conn, creditRequest);
            runner.update(conn, order);
            runner.update(conn, payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getOrderId() {
        String paymentId = "";
        var idSQL = "SELECT payment_id FROM order_entity ORDER BY created DESC LIMIT 1;";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(idSQL)) {
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    paymentId = rs.getString("payment_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentId;
    }

    public static String getPaymentAmount(String paymentId) {
        String amountSQL = String.format("SELECT amount FROM payment_entity WHERE transaction_id='%s';", paymentId);
        String amount = "";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(amountSQL)) {
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    amount = rs.getString("amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    public static String getStatusForPaymentWithDebitCard(String paymentId) {
        String statusSQL = String.format("SELECT status FROM payment_entity WHERE transaction_id='%s';", paymentId);
        String status = "";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getStatusForPaymentWithCreditCard(String paymentId) {
        String statusSQL = String.format("SELECT status FROM credit_request_entity WHERE bank_id='%s';", paymentId);
        String status = "";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
