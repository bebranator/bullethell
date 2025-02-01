package bullethell.entity.movement;

import bullethell.entity.trait.BaseEntity;
import bullethell.entity.trait.Solidc;
import bullethell.utils.Tmp;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

// ill currently stick to the updater thing
public class Move {
    public static Pool<Move> moves = new Pool<>() {
        @Override
        protected Move newObject() {
            return new Move();
        }
    };
    public static Move obtain() {
        return moves.obtain();
    }

    public Vector2 velocity = new Vector2();
    public float acceleration = 1; // to multiply current velocity

    // stop move right at that position
    public void linearMoveTo(Vector2 target, float accel) {
        acceleration = accel;
        velocity.set(target).nor();
    }
    public void nullMovement() {
        acceleration = 1;
        velocity.set(1, 1);
    }

    public void update(BaseEntity solid) {
        Vector2 vel = solid.velocity();
        Vector2 pos = solid.position();
        velocity.scl(acceleration);
        // dumb
        vel.scl(velocity);
    }
}
