package warehouse;

import utils.AutoCloseableExtension;
import utils.SQLExceptionExtension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WarehouseService {

    public void addWarehouse() {

    }

    public List<Warehouse> getAllWarehouses() {

        List<Warehouse> warehouseList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery("SELECT * FROM warehouses");

            while (resultSet.next()) {

                Warehouse warehouse = new Warehouse(resultSet.getLong("id"), resultSet.getString("name"));
                warehouseList.add(warehouse);
            }

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(resultSet);
            AutoCloseableExtension.close(statement);
            AutoCloseableExtension.close(connection);
        }

        return warehouseList;
    }
}
