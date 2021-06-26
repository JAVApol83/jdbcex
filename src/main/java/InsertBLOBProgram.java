import utils.AutoCloseableExtension;
import utils.SQLExceptionExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertBLOBProgram {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("sda-logo-1.png");
        FileInputStream fileInputStream = new FileInputStream(file);
        insertBlob(fileInputStream);
        AutoCloseableExtension.close(fileInputStream);
    }

    private static void insertBlob(FileInputStream fileInputStream) {

        String query = "INSERT INTO blobex " +
                "(data) " +
                "VALUES (?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.prepareStatement(query);
            statement.setBinaryStream(1, fileInputStream);

            statement.executeUpdate();

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(statement, connection);
        }
    }
}
