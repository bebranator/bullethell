package bullethell.game;

import com.badlogic.gdx.math.MathUtils;

public class GameStats {
    public static final int scoreForLife = 100000;
    public static int lives = 2;
    public static int tillNextLive = 5 * 10^6;
    public static int continues = 3;
    public static float power = 0.f;

    public static int score, graze, value;
    public static int displayScore;

    public static void reset() {
        score = 0;
        graze = 0;
        value = 0;
        lives = 2;
        tillNextLive = scoreForLife * lives * lives;
        continues = 5;
        power = 1.f;
        displayScore = score;
    }
    public static void depositScore(int scoreValue) {
        score += scoreValue;
        tillNextLive = Math.max(tillNextLive - scoreValue, 0);

        if(tillNextLive == 0) {
            lives++;
            tillNextLive = (int) (scoreForLife * lives * lives / 1.5f);
        }
    }

    public static void update() {
        displayScore = Math.min(displayScore + 10000, score);
    }
}
