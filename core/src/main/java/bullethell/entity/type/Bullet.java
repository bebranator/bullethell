package bullethell.entity.type;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.EntityGroup;
import bullethell.entity.trait.Solidc;
import bullethell.entity.trait.Timec;
import bullethell.func.Cons;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.module.Fonts;
import bullethell.utils.CPools;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Pool;

import java.lang.module.Configuration;

public class Bullet extends BaseCircleHitboxEntity implements Timec, Pool.Poolable {
    public static int bulletCounter = 0;

    private float time;

    public Circle grazeBox = new Circle();
    public Color color;
    public float lifetime;
    // collision enabler
    public boolean collision;

    public Bullet() {
        setSize(4);
        color = Color.WHITE;
        grazeBox.radius = getSize() * 3.5f;
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        grazeBox.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        grazeBox.setY(y);
    }

    @Override
    public EntityGroup targetGroup() {
        return Vars.enemyBullets;
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
    public void reset() {
        time = 0;
        lifetime = 0;
        color = Color.WHITE;
        velocity().set(0, 0);
        set(0, 0);
        setSize(1);
        setGroup(null);
    }

    @Override
    public void update() {
        super.update();
        updateTime();
    }

    @Override
    public void draw() {
        Draw.color(color);
        Fill.circle(getX(), getY(), getSize());
        Draw.text(Fonts.kelly12, time() + "", getX(), getY());
        Draw.color();
    }

    @Override
    public void added(EntityGroup group) {
        bulletCounter++;
    }

    @Override
    public void removed(EntityGroup group) {
        CPools.free(this);
        bulletCounter--;
    }

    @Override
    public boolean collidesWith(Solidc other) {
        // todo: collisions
        return false;
    }

    public static Bullet spawn(Cons<Bullet> cons) {
        Bullet bullet = CPools.obtain(Bullet.class, Bullet::new);

        cons.get(bullet);
        bullet.add();
        return bullet;
    }
}
