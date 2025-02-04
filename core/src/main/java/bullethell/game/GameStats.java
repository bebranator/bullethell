package bullethell.game;

public class GameStats {
    public static final int scoreForLife = 100000;
    public static int lives = 2;
    public static int tillNextLive = 5 * 10^6;
    public static int continues = 3;
    public static float power = 0.f;

    public static int score, graze, value;

    public static void reset() {
        score = 0;
        graze = 0;
        value = 0;
        lives = 2;
        tillNextLive = scoreForLife * lives * lives;
        continues = 5;
        power = 1.f;
    }
    public static void depositScore(int scoreValue) {
        GameStats.score += score;
        tillNextLive = Math.max(tillNextLive - score, 0);

        if(tillNextLive == 0) {
            lives++;
            tillNextLive = (int) (scoreForLife * lives * lives / 1.5f);
        }
    }
}
