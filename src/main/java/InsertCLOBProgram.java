import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertCLOBProgram {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("file.txt");
        FileReader fileReader = new FileReader(file);
        insertClob(fileReader);
        AutoCloseableExtension.close(fileReader);
    }

    private static void insertClob(InputStreamReader inputStreamReader) {

        String query = "INSERT INTO clobex " +
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
            statement.setCharacterStream(1, inputStreamReader);

            statement.executeUpdate();

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(statement, connection);
        }
    }
}
