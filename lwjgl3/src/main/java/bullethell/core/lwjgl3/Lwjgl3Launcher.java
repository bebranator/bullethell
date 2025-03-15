package bullethell.core.lwjgl3;

import bullethell.core.lwjgl3.dialog.SimpleDialog;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import bullethell.core.Client;

import java.util.Arrays;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return; // This handles macOS support and helps on Windows.
        try {
            createApplication();
        }catch (Exception ex) {
            new SimpleDialog("Closed with error: " + ex.getLocalizedMessage() +
                "\nCause: " + ex.getClass().getName() + "\nMessage: " + stackTrace(ex.getStackTrace())
                , "ERROR").shw();
            ex.printStackTrace();
        }
    }

    private static String stackTrace(StackTraceElement[] trace) {
        StringBuilder builder = new StringBuilder("");
        for (StackTraceElement stackTraceElement : trace) {
            builder.append(stackTraceElement).append("\n");
        }

        return builder.toString();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new Client(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle(Client.TITLE);
        configuration.useVsync(true);
//        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        configuration.setForegroundFPS(Client.FPS);
        configuration.enableGLDebugOutput(true, System.out);
        configuration.setMaximized(true);
        configuration.setResizable(true);
        configuration.setWindowedMode(Client.WIDTH, Client.HEIGHT);
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
