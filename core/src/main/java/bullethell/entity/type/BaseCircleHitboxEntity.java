package bullethell.entity.type;

import bullethell.entity.trait.BaseEntity;
import bullethell.entity.trait.CircleHitboxc;
import com.badlogic.gdx.math.Circle;

public abstract class BaseCircleHitboxEntity extends BaseEntity implements CircleHitboxc {
    protected Circle hitbox = new Circle();

    @Override
    public void setX(float x) {
        super.setX(x);
        hitbox.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        hitbox.setY(y);
    }

    @Override
    public void setSize(float size) {
        hitbox.setRadius(size);
    }

    @Override
    public float getSize() {
        return hitbox.radius;
    }

    @Override
    public Circle hitbox() {
        return hitbox;
    }
}
