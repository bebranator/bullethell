package bullethell.entity.trait;

import bullethell.movement.MovementParams;
import com.badlogic.gdx.math.Vector2;

public interface Movec {
    void setX(float x);
    void setY(float y);

    float getX();
    float getY();

    default void moveBy(float x, float y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    default void set(float x, float y) {
        setX(x);
        setY(y);
    }

    Vector2 position();
    MovementParams params();
}
