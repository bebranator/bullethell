package bullethell.entity.type;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.Collisions;
import bullethell.entity.EntityGroup;
import bullethell.entity.trait.CircleHitboxc;
import bullethell.entity.trait.Solidc;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.module.Fonts;

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

    }

    public void graze(Bullet target) {

    }

    @Override
    public void update() {
        super.update();

        // todo: collision detection
        float x = axis(moveLeft, moveRight) * 3;
        float y = axis(moveDown, moveUp) * 3;

//        tmp.set(x, y);

        velocity().set(x, y);
        Collisions.collision(Vars.enemyBullets, this, this::death);
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
        if(other instanceof CircleHitboxc e) {
            return this.hitbox.overlaps(e.hitbox());
        }
        return false;
    }
}
