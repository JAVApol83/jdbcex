import utils.AutoCloseableExtension;
import utils.SQLExceptionExtension;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class UpdateProductNameCallableStatementProgram {

    /*
        DELIMITER $$
        CREATE PROCEDURE updateProductName (IN product_id BIGINT, INOUT new_name VARCHAR(255))
        BEGIN
	        DECLARE old_name VARCHAR(255) DEFAULT 'Unknown';
            SELECT name INTO old_name FROM products WHERE id = product_id;
            UPDATE products SET name = new_name WHERE id = product_id;
            SET new_name = old_name;
        END $$
        DELIMITER ;
     */

    public static void main(String[] args) {
        updateProductNameCallableStatement(2, "Nowa nazwa produktu 2");
    }

    private static void updateProductNameCallableStatement(long id, String newName) {

        Connection connection = null;
        CallableStatement statement = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.prepareCall("{call updateProductName(?, ?)}");
            statement.setLong(1, id);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.setString(2, newName);

            // statetement nie zwraca ResultSet stąd execute() zwróci false
            // boolean success = statement.execute();

            statement.execute();

            // if (success) {
                String oldName = statement.getString(2);

                System.out.println("Zmieniono nazwę produktu z " + oldName + " na " + newName);
            // }

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(statement, connection);
        }
    }
}
