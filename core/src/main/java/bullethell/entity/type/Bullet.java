package bullethell.entity.type;

import bullethell.content.Bullets;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.Collisions;
import bullethell.entity.EntityGroup;
import bullethell.entity.movement.Move;
import bullethell.entity.trait.Arenac;
import bullethell.entity.trait.Solidc;
import bullethell.entity.trait.Timec;
import bullethell.func.Cons;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.module.Fonts;
import bullethell.type.BulletType;
import bullethell.utils.CPools;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Pool;

import java.lang.module.Configuration;

public class Bullet extends BaseCircleHitboxEntity implements Timec, Pool.Poolable, Arenac {
    public static int bulletCounter = 0;

    private float time = 0;
    public float lifetime, drawSize;
    public Move move = Move.obtain();

    public Color color = Color.WHITE;
    public Cons<Bullet> updater = (e) -> {};
    public Cons<Bullet> outOfBounds = (e) -> {};
    public BulletType type;

    public Bullet() {
        setSize(4);
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
        drawSize = 1;
        color = Color.WHITE;
        updater = (e) -> {};
        outOfBounds = (e) -> {};
        type = Bullets.testBullet;
        move.nullMovement();
        velocity().set(0, 0);
        set(0, 0);
        setSize(1);
    }

    @Override
    public void update() {
        super.update();
        updateTime();
        updater.get(this);
    }

    @Override
    public void draw() {
        Draw.color(color);
        type.draw(this);
        Draw.color();
    }

    @Override
    public void added(EntityGroup group) {
        type.spawned(this);
        bulletCounter++;
    }

    @Override
    public void removed(EntityGroup group) {
        type.death(this);
        CPools.free(this);
        bulletCounter--;
    }

    public static Bullet spawn(Cons<Bullet> cons) {
        Bullet bullet = CPools.obtain(Bullet.class, Bullet::new);

        cons.get(bullet);
        bullet.add();
        return bullet;
    }

    public static PlayerBullet playerBullet(Cons<PlayerBullet> spawn, float x, float y) {
        // set player bullet flag
        PlayerBullet bullet = CPools.obtain(PlayerBullet.class, PlayerBullet::new);
        spawn.get(bullet);
        bullet.set(x, y);
        bullet.add();
        return bullet;
    }

    @Override
    public void hitBounds() {

    }

    @Override
    public void outOfBounds() {

    }
}
