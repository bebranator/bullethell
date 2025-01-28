package bullethell.log;

// todo: write log to files
public class Log {
    private static LogProvider logger;

    public static void log(String prefix, String msg, Object... objects) {
        logger.log(prefix, msg, objects);
    }
    public static void info(String prefix, String msg) {
        logger.log(prefix, msg);
    }
    public static void info(String msg) {
        logger.log("INFO", msg);
    }
    public static void info(Object object) {
        logger.log("INFO", object.toString());
    }
    public static void error(String prefix, String string) {
        logger.error(prefix, string);
    }
    public static void error(String prefix, String string, Throwable threw) {
        logger.error(prefix, string, threw);
    }
    public static void setup() {
        setLogger(new DefaultLoggerProvider());
    }
    // to call print functions
    public static void setLogger(LogProvider provider) {
        logger = provider;
    }

    interface LogProvider {
        void log(String prefix, String string);
        void log(String prefix, String string, Object... objects);

        void error(String prefix, String string);
        void error(String prefix, String string, Throwable threw);
    }
}
