package bullethell.game;

import bullethell.utils.Time;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

// current attack wave
// spellcards are just big waves
public class Attack {
    public float time = 0, lifetime;

    public void superUpdate() {
        time = MathUtils.clamp(time + Time.delta, 0, lifetime);
        update();
    }

    protected void update() {
    }

    // damn how do i implement wait
    public void waitFor(float time) {
    }

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
}
