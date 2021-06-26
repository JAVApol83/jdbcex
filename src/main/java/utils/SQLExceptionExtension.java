package utils;

import java.sql.SQLException;

public class SQLExceptionExtension {

    public static void handleException(Exception exception) {
        System.out.println("Exception message: " + exception.getMessage());

        if (exception instanceof SQLException) {
            SQLException sqlException = (SQLException)exception;
            System.out.println("Error code: " + sqlException.getErrorCode());
            System.out.println("SQL state: " + sqlException.getSQLState());
        }

        System.out.println("Callstack");
        exception.printStackTrace();
    }
}
