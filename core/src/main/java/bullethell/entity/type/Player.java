package bullethell.entity.type;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.Arena;
import bullethell.entity.Collisions;
import bullethell.entity.EntityGroup;
import bullethell.entity.trait.CircleHitboxc;
import bullethell.entity.trait.Solidc;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.module.Fonts;
import bullethell.utils.Tmp;

import static bullethell.module.Bindings.*;

public class Player extends BaseCircleHitboxEntity {
    @Override
    public EntityGroup targetGroup() {
        return Vars.players;
    }

    public Player() {
        setSize(16);
    }

    public void death(Bullet target) {
        Core.app.exit();
    }

    public void graze(Bullet target) {

    }
    Arena arena = Vars.arena;

    @Override
    public void update() {
        super.update();

        // horizontal is faster. this is intended
        float x = axis(moveLeft, moveRight) * 3;
        float y = axis(moveDown, moveUp) * 3;

        //todo: collision with arena
        Tmp.v21.set(getX(), getY()).add(x, y);
        if(!arena.viewport.contains(Tmp.v21)) {
            // todo: figure out in which direction
            // restrict movement

        }
        velocity().set(x, y);

        // check if we about to hit arena bounds


        Collisions.circleWCircle(Vars.enemyBullets, this, this::death);
        Collisions.graze(this, this::graze);
    }

    @Override
    public void draw() {
        Draw.color();
        Fill.circle(getX(), getY(), getSize());
        Draw.text(Fonts.kelly12, "x=" + getX() + "; y=" + getY(), getX(), getY());
    }

    // collide with: fairies, bullets, special map object
    @Override
    public boolean collidesWith(Solidc other) {
        // collides with everyone
//        if(other instanceof CircleHitboxc e) {
//            return this.hitbox.overlaps(e.hitbox());
//        }
//        return false;
        return true;
    }
}
