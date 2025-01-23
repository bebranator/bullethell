package bullethell.utils;

import bullethell.core.Core;
import bullethell.func.Floats;

public class Time {
    public static float delta;
    public static float time, globalTime; // since launch

    public static double timeRaw, globalTimeRaw;

    public static Floats deltaTimeImpl = () -> Math.min(Core.graphics.getDeltaTime() * 60, 3f);

    public static void update() {
        globalTimeRaw += Core.graphics.getDeltaTime() * 60f;
        delta = deltaTimeImpl.get();
        timeRaw += delta;

        if (Double.isInfinite(timeRaw) || Double.isNaN(timeRaw)) {
            timeRaw = 0;
        }

        time = (float) timeRaw;
        globalTime = (float) globalTimeRaw;
    }
//    public static void update(){
//        timeRaw += delta;
//        removal.clear();

//        if(Double.isInfinite(timeRaw) || Double.isNaN(timeRaw)){
//            timeRaw = 0;
//        }
//
//        time = (float)timeRaw;
//        globalTime = (float)globalTimeRaw;

//        for(DelayRun run : runs){
//            run.delay -= delta;
//
//            if(run.delay <= 0){
//                run.finish.run();
//                removal.add(run);
//                Pools.free(run);
//            }
//        }
//
//        runs.removeAll(removal);
//    }
}
