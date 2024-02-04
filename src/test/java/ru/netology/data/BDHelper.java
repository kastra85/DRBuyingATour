package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDHelper {
    private static final QueryRunner runner = new QueryRunner();

    private BDHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.PaymentInfo getSuccessfulPayment() {
        var resultSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var resultPayment = runner.query(conn, resultSQL, new ScalarHandler<String>());
        return new DataHelper.PaymentInfo(resultPayment);
    }

    @SneakyThrows
    public static DataHelper.CreditInfo getSuccessfulCredit() {
        var resultSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        var resultCredit = runner.query(conn, resultSQL, new ScalarHandler<String>());
        return new DataHelper.CreditInfo(resultCredit);
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
    }

    @SneakyThrows
    public static void cleanPaymentStatuses() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
    }

    @SneakyThrows
    public static void cleanCreditStatuses() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
    }
}


