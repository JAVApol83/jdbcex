import utils.AutoCloseableExtension;
import utils.SQLExceptionExtension;

import java.sql.*;

public class InsertProductProgram {

    public static void main(String[] args) {
        insertProduct("Produkt 6", "", 99.99d);
    }

    private static void insertProduct(String name, String description, double price) {

        String query = "INSERT INTO products " +
                "(name, description, price) " +
                "VALUES (?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDouble(3, price);

            int changeCount = statement.executeUpdate();

            System.out.println("Wykonano " + changeCount + " zmian");

            long autoid = 0;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.first()) {
                autoid = resultSet.getLong(1);
            }

            System.out.println("Id nowego rekordu to: " + autoid);

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(resultSet, statement, connection);
        }
    }
}
