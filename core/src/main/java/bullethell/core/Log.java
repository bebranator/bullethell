package bullethell.core;

public class Log {
    public static void log(String prefix, String msg, Object... objects) {
        String result = String.format(msg, objects);

        Core.app.log(prefix, result);
    }
    public static void info(String msg) {
        Core.app.log("INFO", msg);
    }
}
