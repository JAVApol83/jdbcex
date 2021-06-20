import java.sql.*;

public class PrintDataListProgram {

    public static void main(String[] args) throws SQLException {
        printDataList();
    }

    private static void printDataList() {


        //CREATE TABLE products (
        //	id bigint not null auto_increment,
        //    name varchar(255) not null,
        //    description varchar(255),
        //    price decimal(6,2) not null,
        //    primary key (id)
        //)


        // pobranie i wyświetlenie danych z tabeli products

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {

                System.out.print(resultSet.getLong(1));
                System.out.print(" | ");
                System.out.print(resultSet.getString(2));
                System.out.print(" | ");
                System.out.print(resultSet.getString(3));
                System.out.print(" | ");
                System.out.print(resultSet.getDouble(4));
                System.out.println();
            }

            System.out.println("*******************************");

            resultSet.beforeFirst();

            while (resultSet.next()) {

                System.out.print(resultSet.getLong("id"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("description"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("name"));
                System.out.print(" | ");
                System.out.print(resultSet.getDouble("price"));
                System.out.println();
            }


        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(resultSet);
            AutoCloseableExtension.close(statement);
            AutoCloseableExtension.close(connection);
        }
    }
}
