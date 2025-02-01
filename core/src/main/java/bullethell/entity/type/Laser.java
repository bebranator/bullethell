package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.EntityGroup;
import bullethell.entity.trait.BaseEntity;
import bullethell.entity.trait.Solidc;
import bullethell.entity.trait.Timec;
import bullethell.func.Cons;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.utils.CPools;
import bullethell.utils.CRectangle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

public class Laser extends BaseEntity implements Solidc, Timec, Pool.Poolable {
    public float time, lifetime;
    public CRectangle hitbox = new CRectangle();

    // always render
    @Override
    public void update() {
        updateTime();
        super.update();
    }

    @Override
    public void draw() {
        Fill.filled();
        Fill.rect(getX(), getY(), hitbox.originX, hitbox.originY, hitbox.w, hitbox.h, hitbox.rotation);
    }

    @Override
    public void reset() {
        time = 0;
        lifetime = 0;
        set(0, 0);
        hitbox.rotation = 0;
    }

    @Override
    public void removed(EntityGroup group) {
        CPools.free(this);
    }

    public void setBounds(float w, float h) {
        hitbox.w = w;
        hitbox.h = h;
    }
    public void center() {
        hitbox.originX = hitbox.w / 2;
        hitbox.originY = hitbox.h / 2;
    }

    public void rotate(float rot) {
        hitbox.rotate(rot);
    }
    public void setRotation(float r) {
        hitbox.setRotation(r);
    }

    public static Laser spawn(Cons<Laser> cons) {
        Laser laser = CPools.obtain(Laser.class, Laser::new);
        cons.get(laser);

        laser.add();
        return laser;
    }

    /// JUNK

    @Override
    public void setX(float x) {
        super.setX(x);
        hitbox.x = x;
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        hitbox.y = y;
    }

    @Override
    public float lifetime() {
        return lifetime;
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
        return Vars.lasers;
    }

    @Override
    public boolean intersect(Rectangle rect) {
        return true;
    }
}
