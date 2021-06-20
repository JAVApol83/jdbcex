public class AutoCloseableExtension {

    public static void close(AutoCloseable autoCloseable) {

        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(AutoCloseable... autoCloseable) {
        for(AutoCloseable ac : autoCloseable) {
            close(ac);
        }
    }
}
