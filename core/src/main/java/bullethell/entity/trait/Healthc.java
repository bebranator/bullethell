package bullethell.entity.trait;

import com.badlogic.gdx.math.MathUtils;

public interface Healthc {
    default void takeDamage(float damage) {
        float newHealth = Math.max(health() - damage, 0);
        damaged(damage, newHealth);

        health(newHealth);

        if(health() == 0) {
            ranOutOfHealth();
        }
    }
    default void heal(float value) {
        // no 0 health on heal
        health(MathUtils.clamp(health() + value, value, maxHealth()));
    }

    void damaged(float value, float newHealth);

    float maxHealth();
    float health();
    // sets current value
    void health(float value);
    // ran out of health
    void ranOutOfHealth();
}
