import utils.AutoCloseableExtension;
import utils.SQLExceptionExtension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteProductProgram {

    public static void main(String[] args) {
        System.out.println("Usuwam produkt o id = 5. Powodzenie? " + deleteProduct(5));
    }

    private static boolean deleteProduct(long id) {

        String query = "DELETE FROM products " +
                "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.prepareStatement(query);
            statement.setLong(1, id);

            int countDeletes = statement.executeUpdate();
            return countDeletes > 0;

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(statement, connection);
        }

        return false;
    }
}
