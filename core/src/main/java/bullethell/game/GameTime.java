package bullethell.game;

import bullethell.utils.Time;

public class GameTime {
    public static float delta; // do not change
    public static float deltaModifier = 1f;
    public static float time; // since start

    public static void update() {
        delta = Time.delta * deltaModifier;
        time += delta;
    }

    public static void reset() {
        time = 0f;
        deltaModifier = 1f;
        delta = 1f;
    }
    // task begins after some time
    public static void schedule(Runnable runnable, float time) {

    }
}
