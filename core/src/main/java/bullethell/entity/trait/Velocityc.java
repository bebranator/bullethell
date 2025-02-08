package bullethell.entity.trait;

import bullethell.game.GameTime;
import bullethell.utils.Time;
import com.badlogic.gdx.math.Vector2;

public interface Velocityc extends Movec {
    float speed();
    Vector2 velocity();

    default void updateVelocity() {
        set(getX() + velocity().x * GameTime.delta * speed(), getY() + velocity().y * GameTime.delta * speed());
    }
}
