package bullethell.entity;

import bullethell.core.Client;
import bullethell.entity.trait.Arenac;
import bullethell.entity.trait.CircleHitboxc;
import bullethell.entity.type.Laser;
import com.badlogic.gdx.math.Rectangle;

// todo: world stuff (block moving, resize arena)
// todo: if player gets out of bounds: kill or push towards arena?
public class Arena {
    public Rectangle world;
    public Rectangle viewport;

    public Arena(float x, float y, float w, float h) {
        world = new Rectangle(x, y, w, h);
        viewport = new Rectangle(x, y, w, h);
    }
    public Arena() {
        world = new Rectangle();
        viewport = new Rectangle();
        defaults();
    }

    public void defaults() {
        world.set(40, 40, Client.WIDTH / 1.375f - 80, Client.HEIGHT - 80f);
        viewport.set(world);
    }

    public boolean outOfBounds(Arenac ent) {
        // if not solid: just check coordinates

        if(ent instanceof CircleHitboxc circle) {
            return !world.contains(circle.hitbox());
        }

        return !world.contains(ent.getX(), ent.getY());
    }

    public void updateGroup(EntityGroup<? extends Arenac> entity) {
        entity.forEach(e -> {
            if(outOfBounds(e)) e.outOfBounds();
        });
    }
}
