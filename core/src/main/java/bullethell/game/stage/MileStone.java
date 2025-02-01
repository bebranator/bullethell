package bullethell.game.stage;

import bullethell.utils.Time;
import com.badlogic.gdx.math.MathUtils;

// represents point on timeline
// when reached: execute code inside
// example
/*
MileStone fairyCircles = new MileStone() {
    @Override
    public void update() {
        float time = this.time;

        // 1 on easy, normal, hard and 4 on lunatic
        int diff = difficulty(1, 1, 1, 4);
        // wait 10 seconds then execute
        wait(10 * 60, () -> {

        });
        spawn(FairyRed, Move.sine());
    }
}
 */
public class MileStone {
    public float time, lifetime;
    public boolean alive;

    // called by timeline when begin task
    public void begin() {
    }

    // called when updating timeline
    public void update() {
        time = MathUtils.clamp(time + Time.delta, 0, lifetime);

        if(time >= lifetime) {
            alive = false;
            return;
        }
        tick();
    }

    protected void tick() {

    }

    // called by timeline when ending task
    public void end() {
    }
}
