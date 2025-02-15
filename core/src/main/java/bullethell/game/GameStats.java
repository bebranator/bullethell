package bullethell.game;

import bullethell.core.Vars;
import com.badlogic.gdx.math.MathUtils;

public class GameStats {
    public static int deaths;

    public static final int scoreForLife = 750000;
    public static int lives = 2;
    public static int tillNextLive = 5 * 10^6;
    public static int continues = 3;
    public static int spells = 3;

    public static int score, graze, value;
    public static int displayScore;

    public static float power = 0.f;
    public static float damage = 0, spellDamage; // damage dealt to boss while boss spell was active

    public static void reset() {
        score = 0;
        graze = 0;
        value = 0;
        lives = 2;
        spells = 3;
        continues = 5;
        power = 1.f;
        displayScore = score;
        deaths = 0;
        recalculateLifeCost();
        damage = 0;
        spellDamage = 0;
    }
    public static void addSpell() {
        spells++;
    }
    public static void removeSpell() {
        spells--;
    }

    public static void removeLife() {
        lives--;
        recalculateLifeCost();
    }
    public static void addLife() {
        lives++;
        recalculateLifeCost();
    }
    public static void depositScore(int scoreValue) {
        score += scoreValue;

        tillNextLive = Math.max(tillNextLive - scoreValue, 0);

        if(tillNextLive == 0) {
            addLife();
        }
    }
    public static void recalculateLifeCost() {
        tillNextLive = scoreForLife * lives * lives * 2;
    }
    public static void damage(float finalDamage) {
        damage += finalDamage;

        if(Vars.game.bossSpell()) {
            spellDamage += finalDamage;
        }
    }

    public static void update() {
        displayScore = Math.min(displayScore + 40000, score);
    }
}
