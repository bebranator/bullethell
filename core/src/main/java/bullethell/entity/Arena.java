package bullethell.entity;

import bullethell.core.Client;
import bullethell.core.Vars;
import bullethell.entity.trait.Arenac;
import bullethell.entity.trait.CircleHitboxc;
import com.badlogic.gdx.math.Rectangle;

public class Arena {
    public Rectangle world;
    public Rectangle viewport;
    // used for entity drawing
    public Rectangle drawViewport; // ig

    public Arena(float x, float y, float w, float h) {
        world = new Rectangle(x, y, w, h);
        viewport = new Rectangle(x, y, w, h);
    }
    public Arena() {
        world = new Rectangle();
        viewport = new Rectangle();
        drawViewport = new Rectangle();
        defaults();
    }

    public void defaults() {
//        world.set(40, 40, Client.WIDTH / 1.375f - 80, Client.HEIGHT - 80f);
//        viewport.set(world);
        world.set(0, 0, Client.WIDTH / 1.375f - 80, Client.HEIGHT - 80);
        viewport.set(40, 40, world.width, world.height);
        drawViewport.set(viewport);
        drawViewport.x = 0;
        drawViewport.y = 0;
    }
    public void set(float x, float y, float w, float h) {
        world.set(x, y, w, h);
        viewport.set(world);
        drawViewport.set(viewport);

        recalculateFrameBuffer(w, h);
    }
    public void recalculateFrameBuffer(float w, float h) {
        if(Vars.renderer != null) Vars.renderer.resizeBuffer((int) w, (int) h);
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
