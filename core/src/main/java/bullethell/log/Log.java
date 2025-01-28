package bullethell.log;

// todo: write log to files
public class Log {
    private static LogProvider logger;

    public static void log(String msg, Object... objects) {
        logger.log(msg, objects);
    }
    public static void info(String msg) {
        logger.log(msg);
    }
    public static void info(Object object) {
        logger.log(object.toString());
    }
    public static void error(String prefix, String string) {
        logger.error(string);
    }
    public static void error(String prefix, String string, Throwable threw) {
        logger.error(string, threw);
    }
    public static void setup() {
        setLogger(new DefaultLoggerProvider());
    }
    // to call print functions
    public static void setLogger(LogProvider provider) {
        logger = provider;
    }

    interface LogProvider {
        void log(String string);
        void log(String string, Object... objects);

        void error(String string);
        void error(String string, Throwable threw);
    }
}
