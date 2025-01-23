package bullethell.entity.trait;

import bullethell.entity.EntityGroup;
import com.badlogic.gdx.math.Vector2;

// implements some methods on entityc
public abstract class BaseEntity implements Entityc, Velocityc, Rotationc {
    private float x = 0, y = 0, drag = 0;
    private Vector2 velocity = new Vector2(), rotation = new Vector2();

    private EntityGroup group;

    @Override
    public Vector2 rotation() {
        return rotation;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public Vector2 velocity() {
        return velocity;
    }

    @Override
    public float drag() {
        return drag;
    }

    @Override
    public void draw() {}

    @Override
    public void update() {
        updateVelocity();
    }

    @Override
    public EntityGroup getGroup() {
        return group;
    }

    @Override
    public void setGroup(EntityGroup group) {
        this.group = group;
    }
}
