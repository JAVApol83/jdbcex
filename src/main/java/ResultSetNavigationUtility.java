import java.sql.ResultSet;

public class ResultSetNavigationUtility {

    private ResultSet resultSet;

    public ResultSetNavigationUtility(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void printForward() throws Exception {

        while (resultSet.next()) {
            printResultSetItem();
        }
    }

    public void printFirst() throws Exception {

        if (resultSet.first()) {
            printResultSetItem();
        }
    }

    public void printLast() throws Exception {

        if (resultSet.last()) {
            printResultSetItem();
        }
    }

    public void printAt(int index) throws Exception {

        if (resultSet.absolute(index)) {
            printResultSetItem();
        }
    }

    public void printRelative(int index) throws Exception {

        if (resultSet.relative(index)) {
            printResultSetItem();
        }
    }

    public void printReverse() throws Exception {

        resultSet.afterLast();
        while (resultSet.previous()) {
            printResultSetItem();
        }
    }

    private void printResultSetItem() throws Exception {

        System.out.print(resultSet.getLong("id"));
        System.out.print(" | ");
        System.out.print(resultSet.getString("description"));
        System.out.print(" | ");
        System.out.print(resultSet.getString("name"));
        System.out.print(" | ");
        System.out.print(resultSet.getDouble("price"));
        System.out.println();
    }
}
