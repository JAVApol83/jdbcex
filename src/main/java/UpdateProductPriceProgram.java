import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateProductPriceProgram {

    public static void main(String[] args) {
        updateProductPrice("Produkt 1", 9.99d);
    }

    private static void updateProductPrice(String name, double price) {

        String query = "UPDATE products " +
                "SET price = ? " +
                "WHERE name = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.prepareStatement(query);
            statement.setDouble(1, price);
            statement.setString(2, name);

            int changeCount = statement.executeUpdate();

            System.out.println("Wykonano " + changeCount + " zmian");

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(statement, connection);
        }
    }
}
