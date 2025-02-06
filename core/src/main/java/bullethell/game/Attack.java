package bullethell.game;

import bullethell.core.Vars;
import bullethell.entity.type.Player;
import bullethell.utils.Time;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

// current attack wave
// spell cards are just big waves
public class Attack {
    public float time = 0, lifetime;

    public void superUpdate() {
        time = MathUtils.clamp(time + Time.delta, 0, lifetime);
        update();
    }

    protected void update() {
    }

    public void draw() {}

    // check if we should stop current attack
    public boolean isEnd() {
        return time >= lifetime;
    }

    public void schedule(Runnable runnable, float period, float interval, int count) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                runnable.run();
            }
        }, period, interval, count);
    }

    public void end() {
        lifetime = 0;
        time = 0;
    }

    public float byDifficulty(float easy, float normal, float hard, float expert, float lunatic) {
        return switch (Vars.game.difficulty) {
            case easy: yield easy;
            case normal: yield normal;
            case hard: yield hard;
            case expert: yield expert;
            case lunatic: yield lunatic;
        };
    }
    public int byDifficulty(int easy, int normal, int hard, int expert, int lunatic) {
        return switch (Vars.game.difficulty) {
            case easy: yield easy;
            case normal: yield normal;
            case hard: yield hard;
            case expert: yield expert;
            case lunatic: yield lunatic;
        };
    }
    // arena width
    public float width() {
        return Vars.arena.world.width;
    }

    public float height() {
        return Vars.arena.world.height;
    }

    public float ax() {
        return Vars.arena.world.x;
    }
    public float ay() {
        return Vars.arena.world.y;
    }

    public Player player() {
        return Vars.player;
    }
}
