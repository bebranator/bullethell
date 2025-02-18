package bullethell.log;

import bullethell.core.Core;

public class GdxLoggerProvider implements Log.LogProvider {
    @Override
    public void log(String string) {
        Core.app.log("LOG", string);
    }

    @Override
    public void log(String string, Object... objects) {
        Core.app.log("LOG", String.format(string, objects));
    }

    @Override
    public void error(String string) {
        Core.app.error("ERROR", string);
    }

    @Override
    public void error(String string, Throwable threw) {
        Core.app.error("ERROR", string, threw);
    }
}
