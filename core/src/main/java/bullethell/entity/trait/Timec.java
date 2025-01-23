package bullethell.entity.trait;

import bullethell.utils.Time;
import com.badlogic.gdx.math.MathUtils;

public interface Timec extends Entityc {
    float lifetime();
    float time();
    void time(float time);

    default void updateTime() {
        // if lifetime is negative: bullet is immortal to time
        if(lifetime() < 0) return;

        time(MathUtils.clamp(time() + Time.delta, 0, lifetime()));

        if(time() >= lifetime()) remove();
    }
}
