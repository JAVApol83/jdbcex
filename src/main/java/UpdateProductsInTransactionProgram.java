import utils.AutoCloseableExtension;
import utils.SQLExceptionExtension;

import java.sql.*;

public class UpdateProductsInTransactionProgram {

    /*
    CREATE TABLE productDetails (
	    id BIGINT NOT NULL AUTO_INCREMENT,
        manufacturer VARCHAR(255) NOT NULL,
        count INT NOT NULL DEFAULT 0,
        productID BIGINT NOT NULL,
        PRIMARY KEY(id),
        FOREIGN KEY (productID) REFERENCES products (id));
     */

    public static void main(String[] args) {

        // failUpdateWithoutTransaction();
        // failUpdateWithTransaction();
        successUpdateWithTransaction();
    }

    private static String queryUpdateProducts = "INSERT INTO products " +
            "(name, description, price) " +
            "VALUES (?, ?, ?)";

    private static String queryUpdateProductDetails = "INSERT INTO productDetails " +
            "(manufacturer, count, productId) " +
            "VALUES (?, ?, ?)";

    private static String queryUpdateProductDetailsFail = "INSERT INTO productDetails " +
            "(manufacturer, count_x, productId) " +
            "VALUES (?, ?, ?)";

    private static void failUpdateWithoutTransaction() {

        Connection connection = null;
        PreparedStatement statementProducts = null;
        PreparedStatement statementProductDetails = null;
        ResultSet resultSet = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statementProducts = connection.prepareStatement(queryUpdateProducts, Statement.RETURN_GENERATED_KEYS);
            statementProducts.setString(1, "Product without product details");
            statementProducts.setString(2, "");
            statementProducts.setDouble(3, 11.99d);

            statementProducts.executeUpdate();

            resultSet = statementProducts.getGeneratedKeys();
            long productId = 0;

            if (resultSet.next()) {
                productId = resultSet.getLong(1);
            } else {
                return;
            }

            statementProductDetails = connection.prepareStatement(queryUpdateProductDetailsFail);
            statementProductDetails.setString(1, "IKEA");
            statementProductDetails.setInt(2, 100);
            statementProductDetails.setLong(3, productId);

            statementProductDetails.executeUpdate();

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(resultSet, statementProductDetails, statementProducts, connection);
        }
    }

    private static void failUpdateWithTransaction() {

        Connection connection = null;
        PreparedStatement statementProducts = null;
        PreparedStatement statementProductDetails = null;
        ResultSet resultSet = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            connection.setAutoCommit(false);

            statementProducts = connection.prepareStatement(queryUpdateProducts, Statement.RETURN_GENERATED_KEYS);
            statementProducts.setString(1, "Product without product details");
            statementProducts.setString(2, "");
            statementProducts.setDouble(3, 11.99d);

            statementProducts.executeUpdate();

            resultSet = statementProducts.getGeneratedKeys();
            long productId = 0;

            if (resultSet.next()) {
                productId = resultSet.getLong(1);
            } else {
                return;
            }

            statementProductDetails = connection.prepareStatement(queryUpdateProductDetailsFail);
            statementProductDetails.setString(1, "IKEA");
            statementProductDetails.setInt(2, 100);
            statementProductDetails.setLong(3, productId);

            statementProductDetails.executeUpdate();

            connection.commit();

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);

            try {
                connection.rollback();
            } catch (SQLException throwables) {
                SQLExceptionExtension.handleException(throwables);
            }
        } finally {
            AutoCloseableExtension.close(resultSet, statementProductDetails, statementProducts, connection);
        }
    }

    private static void successUpdateWithTransaction() {

        Connection connection = null;
        PreparedStatement statementProducts = null;
        PreparedStatement statementProductDetails = null;
        ResultSet resultSet = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            connection.setAutoCommit(false);

            statementProducts = connection.prepareStatement(queryUpdateProducts, Statement.RETURN_GENERATED_KEYS);
            statementProducts.setString(1, "Product without product details");
            statementProducts.setString(2, "");
            statementProducts.setDouble(3, 11.99d);

            statementProducts.executeUpdate();

            resultSet = statementProducts.getGeneratedKeys();
            long productId = 0;

            if (resultSet.next()) {
                productId = resultSet.getLong(1);
            } else {
                return;
            }

            statementProductDetails = connection.prepareStatement(queryUpdateProductDetails);
            statementProductDetails.setString(1, "IKEA");
            statementProductDetails.setInt(2, 100);
            statementProductDetails.setLong(3, productId);

            statementProductDetails.executeUpdate();

            connection.commit();

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);

            try {
                connection.rollback();
            } catch (SQLException throwables) {
                SQLExceptionExtension.handleException(throwables);
            }
        } finally {
            AutoCloseableExtension.close(resultSet, statementProductDetails, statementProducts, connection);
        }
    }
}
