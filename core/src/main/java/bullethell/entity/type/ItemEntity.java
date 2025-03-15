package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.EntityGroup;
import bullethell.entity.trait.Arenac;
import bullethell.entity.trait.Timec;
import com.badlogic.gdx.utils.Pool;

public class ItemEntity extends BaseCircleHitboxEntity implements Timec, Pool.Poolable, Arenac {
    protected float time;

    @Override
    public void hitBounds() {

    }

    @Override
    public void outOfBounds() {
    }

    @Override
    public float lifetime() {
        return 30 * 60;
    }

    @Override
    public float time() {
        return time;
    }

    @Override
    public void time(float time) {
        this.time = time;
    }

    @Override
    public EntityGroup targetGroup() {
        return Vars.items;
    }

    @Override
    public void reset() {

    }
}
