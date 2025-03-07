package bullethell.entity.trait;

import bullethell.entity.EntityGroup;
import bullethell.game.GameTime;
import bullethell.movement.MovementParams;
import com.badlogic.gdx.math.Vector2;

// implements some methods on entityc
public abstract class BaseEntity implements Entityc{
    protected MovementParams params = new MovementParams();
    private EntityGroup group;

    private final Vector2 position = new Vector2();

    protected float drawSize;
    protected float birthTime;

    @Override
    public float birthTime() {
        return birthTime;
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
    public Vector2 position() {
        return position;
    }

    @Override
    public void draw() {}

    @Override
    public void update() {}

    @Override
    public EntityGroup getGroup() {
        return group;
    }

    @Override
    public void setGroup(EntityGroup group) {
        this.group = group;
    }

    @Override
    public float drawSize() {
        return drawSize;
    }

    @Override
    public void drawSize(float value) {
        this.drawSize = value;
    }

    @Override
    public MovementParams params() {
        return params;
    }
}
