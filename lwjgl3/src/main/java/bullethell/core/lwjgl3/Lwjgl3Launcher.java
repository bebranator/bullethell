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
        //// Vsync limits the frames per second to what your hardware can display, and helps eliminate
        //// screen tearing. This setting doesn't always work on Linux, so the line after is a safeguard.
        configuration.useVsync(true);
        //// Limits FPS to the refresh rate of the currently active monitor, plus 1 to try to match fractional
        //// refresh rates. The Vsync setting above should limit the actual FPS to match the monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        configuration.enableGLDebugOutput(true, System.out);
        configuration.setMaximized(true);
        configuration.setResizable(true);
        //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        //// useful for testing performance, but can also be very stressful to some hardware.
        //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.
        configuration.setWindowedMode(Client.WIDTH, Client.HEIGHT);
//        configuration.setWindowPosition(0, 0);
        //// You can change these files; they are in lwjgl3/src/main/resources/ .
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }
}
