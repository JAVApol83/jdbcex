import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class PrintDataListByPriceCallableStatementProgram {

    /*
        DELIMITER $$
        CREATE PROCEDURE listProductsByPrice (IN priceFrom DECIMAL(6,2), IN priceTo DECIMAL(6,2))
        BEGIN
	        SELECT * FROM products WHERE price BETWEEN priceFrom AND priceTo;
        END $$
        DELIMITER ;
     */

    public static void main(String[] args) {
        printDataListByPrice(10.0d, 25.0d);
    }

    private static void printDataListByPrice(double lowPrice, double highPrice) {

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.prepareCall("{call listProductsByPrice(?, ?)}");
            statement.setDouble(1, lowPrice);
            statement.setDouble(2, highPrice);

            boolean success = statement.execute();
            if (success) {

                resultSet = statement.getResultSet();

                ResultSetNavigationUtility resultSetNavigationUtility = new ResultSetNavigationUtility(resultSet);
                resultSetNavigationUtility.printForward();
            }

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(resultSet, statement, connection);
        }
    }
}
