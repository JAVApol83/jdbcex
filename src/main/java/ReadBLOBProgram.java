import utils.AutoCloseableExtension;
import utils.SQLExceptionExtension;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReadBLOBProgram {

    public static void main(String[] args) throws Exception {
        String fileName = "sda.png";
        InputStream inputStream = readBlob();
        if (inputStream != null) {
            File file = new File(fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
        }
    }

    private static InputStream readBlob() {

        String query = "SELECT data FROM blobex LIMIT 1";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                    );

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBinaryStream(1);
            }

        } catch (Exception exception) {
            SQLExceptionExtension.handleException(exception);
        } finally {
            AutoCloseableExtension.close(resultSet, statement, connection);
        }

        return null;
    }
}
