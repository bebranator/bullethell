package bullethell.entity.trait;

import com.badlogic.gdx.math.MathUtils;

public interface Healthc {
    default void takeDamage(float damage) {
        float newHealth = Math.max(health() - damage * damageScale(), 0);
        damaged(damage, newHealth);

        health(newHealth);

        if(health() == 0) {
            ranOutOfHealth();
        }
    }
    // not expecting negative numbers btw, since we have takedamage()
    default void heal(float value) {
        // no 0 health on heal
        health(Math.min(health() + value, maxHealth()));
    }

    float maxHealth();

    void maxHealth(float value);
    float health();

    void health(float value);
    float damageScale();

    void damageScale(float value);
    // ran out of health

    default void ranOutOfHealth() {}
    default void damaged(float value, float newHealth) {}
}
