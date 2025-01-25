package bullethell.game;

import com.badlogic.gdx.math.MathUtils;

public class Stats {
    public static int lives = 2;
    public static int tillNextLive = 5 * 10^6;
    public static int continues = 3;
    public static float power = 0.f;

    public static int score, graze;

    public static void reset() {
        score = 0;
        graze = 0;
        lives = 2;
        tillNextLive = 5 * 10^6;
        continues = 3;
        power = 0.f;
    }
    public static void score(int score) {
        Stats.score += score;
        tillNextLive = Math.max(tillNextLive - score, 0);

        if(tillNextLive == 0) lives++;
    }
}
