import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PrintDataListByPriceProgram {

    public static void main(String[] args) {
        printDataListByPrice(10.0d, 25.0d);
        System.out.println();
        printDataListByPrice(0.0d, 10.0d);
        System.out.println();
        printDataListByPrice(25.0d, 50.0d);
    }

    // metoda zwracająca produkty w zakresie kwoty minimalnej i maksymalnej

    private static void printDataListByPrice(double lowPrice, double highPrice) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.prepareStatement("SELECT * FROM products WHERE price BETWEEN ? AND ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            statement.setDouble(1, lowPrice);
            statement.setDouble(2, highPrice);

            resultSet = statement.executeQuery();

            ResultSetNavigationUtility resultSetNavigationUtility = new ResultSetNavigationUtility(resultSet);
            resultSetNavigationUtility.printForward();

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(resultSet, statement, connection);
        }
    }
}
