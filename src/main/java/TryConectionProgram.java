import java.sql.Connection;
import java.sql.DriverManager;

public class TryConectionProgram {

    public static void main(String[] args) throws Exception {

        System.out.println("Połączenie do bazy danych: " + tryConnect());
    }

    private static boolean tryConnect() throws Exception {

        // Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sdapol83_jdbcex", "root", "Torun2020"
                );

        // jdbc:<driver>:<host>/<database_name> username password

        // Oracle
        // "jdbc:oracle:thin:@myhost:1521:orcl", "user", "password"

        // SQL Server
        // "jdbc:sqlserver://localhost;user=user;password=password"

        boolean isValid = connection.isValid(2);

        connection.close();

        return isValid;
    }
}
