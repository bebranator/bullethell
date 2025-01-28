package bullethell.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultLoggerProvider implements Log.LogProvider {
    static Logger log = Logger.getLogger("app");

    @Override
    public void log(String string) {
        log.log(Level.INFO, string);
    }

    @Override
    public void log(String string, Object... objects) {
        log.log(Level.INFO, string, objects);
    }

    @Override
    public void error(String string) {
        log.log(Level.SEVERE, string);
    }

    @Override
    public void error(String string, Throwable threw) {
        log.log(Level.SEVERE, string, threw);
    }
}
