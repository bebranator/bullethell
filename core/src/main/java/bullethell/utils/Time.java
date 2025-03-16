package bullethell.utils;

import bullethell.core.Core;
import bullethell.func.Floats;

public class Time {
    public static float delta;
    public static float time, globalTime; // since launch

    public static double timeRaw, globalTimeRaw;

    public static Floats deltaSupplier = () -> Math.min(Core.graphics.getDeltaTime() * 60, 3f);

    public static void update() {
        globalTimeRaw += Core.graphics.getDeltaTime() * 60f;
        delta = deltaSupplier.get();
        timeRaw += delta;

        if (Double.isInfinite(timeRaw) || Double.isNaN(timeRaw)) {
            timeRaw = 0;
        }

        time = (float) timeRaw;
        globalTime = (float) globalTimeRaw;
    }
}
