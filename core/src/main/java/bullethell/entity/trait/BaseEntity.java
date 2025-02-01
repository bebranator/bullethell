package bullethell.entity.trait;

import bullethell.entity.EntityGroup;
import com.badlogic.gdx.math.Vector2;

// implements some methods on entityc
public abstract class BaseEntity implements Entityc, Velocityc, Rotationc {
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2(), rotation = new Vector2();

    private EntityGroup group;
    private float drag = 0f;

    @Override
    public Vector2 rotation() {
        return rotation;
    }

    @Override
    public float getY() {
        return position.y;
    }

    @Override
    public float getX() {
        return position.x;
    }

    @Override
    public void setY(float y) {
        this.position.y = y;
    }

    @Override
    public void setX(float x) {
        this.position.x = x;
    }

    @Override
    public Vector2 velocity() {
        return velocity;
    }

    @Override
    public Vector2 position() {
        return position;
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
