package bullethell.entity.type;

import bullethell.content.Bullets;
import bullethell.core.Vars;
import bullethell.entity.EntityGroup;
import bullethell.entity.EntityUpdater;
import bullethell.entity.trait.Arenac;
import bullethell.entity.trait.Timec;
import bullethell.func.Cons;
import bullethell.game.GameTime;
import bullethell.movement.MovementParams;
import bullethell.movement.Mover;
import bullethell.type.BulletType;
import bullethell.utils.CPools;
import com.badlogic.gdx.utils.Pool;

public class Bullet extends BaseCircleHitboxEntity implements Timec, Pool.Poolable, Arenac {
    public static int bulletCounter = 0;

    private float time = 0;
    public float lifetime;

    public EntityUpdater<Bullet> updater = (e) -> {};
    public Cons<Bullet> outOfBounds = (e) -> {};
    public BulletType type;
    public boolean enabled;

    public Bullet() {
        setSize(4);
        enabled = true;
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

        updater = (e) -> {};
        outOfBounds = (e) -> {};

        type = Bullets.testBullet;

        set(0, 0);
        setSize(1);
    }

    @Override
    public void update() {
        if(!enabled) return;

        super.update();

        updateTime();

        updater.update(this);

        Mover.update(this, params());
    }

    @Override
    public void draw() {
        if(!enabled) return;
        type.draw(this);
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

    @Override
    public void hitBounds() {

    }

    @Override
    public void outOfBounds() {

    }

    public static Bullet spawn(Cons<Bullet> cons) {
        Bullet bullet = CPools.obtain(Bullet.class, Bullet::new);

        bullet.lifetime = 600;
        cons.get(bullet);
        bullet.add();
        return bullet;
    }

    public static Bullet spawn(Cons<Bullet> cons, BulletType type, float x, float y) {
        Bullet bullet = CPools.obtain(Bullet.class, Bullet::new);
        bullet.lifetime = 600;
        bullet.type = type;
        cons.get(bullet);
        bullet.set(x, y);
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
}
