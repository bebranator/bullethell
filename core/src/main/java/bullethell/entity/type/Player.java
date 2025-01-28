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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

import static bullethell.module.Bindings.*;
import static bullethell.core.Vars.*;

public class Player extends BaseCircleHitboxEntity {

    @Override
    public void setY(float y) {
        y = MathUtils.clamp(y, arena.world.y + getSize(), arena.world.y + arena.world.height - getSize());
        super.setY(y);
    }

    @Override
    public void setX(float x) {
        x = MathUtils.clamp(x, arena.world.x + getSize(), arena.world.x + arena.world.width - getSize());
        super.setX(x);
    }

    @Override
    public EntityGroup targetGroup() {
        return Vars.players;
    }

    public Player() {
        setSize(6);
    }

    public void death(Bullet target) {
        Core.app.exit();
    }

    public void graze(Bullet target) {

    }

    @Override
    public void update() {
        super.update();
        Collisions.circleWCircle(Vars.enemyBullets, this, this::death);
        Collisions.graze(this, this::graze);

        // horizontal is faster. this is intended
        float x = axis(moveLeft, moveRight) * 6;
        float y = axis(moveDown, moveUp) * 6;

        //todo: collision with arena
//        Tmp.c1.set(getX() + x, getY() + y, getSize());
//
//        if(!arena.viewport.contains(Tmp.c1)) {
//            // todo: figure out in which direction
//            // restrict movement
//            velocity().set(0, 0);
//        }else {
            velocity().set(x, y).scl(Core.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? .33f : 1);
//        }

        // check if we about to hit arena bounds
    }

    @Override
    public void draw() {
        Draw.color();
        Fill.filled();
        Fill.circle(getX(), getY(), getSize());
        Fill.line();
        Draw.textMode();
        Draw.text(Fonts.kelly12, "x=" + getX() + "; y=" + getY(), getX(), getY());
        Draw.textEnd();
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
