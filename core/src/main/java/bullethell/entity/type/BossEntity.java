package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.Collisions;
import bullethell.entity.EntityGroup;
import bullethell.func.Cons;
import bullethell.game.GameStats;
import bullethell.type.BossType;
import bullethell.utils.CPools;

// actually i dont think we need to change hitbox size for boss
public class BossEntity extends BaseCircleHitboxEntity {
    private BossType type;
    public boolean invulnerable;
    public float vulnerability = 1; // from 0 to 1
    public float health;

    @Override
    public EntityGroup targetGroup() {
        return Vars.bossGroup;
    }

    @Override
    public void update() {
        super.update();

        if (!invulnerable) {
            Collisions.playerBullets(this, this::takeBulletDamage);
        }
    }

    // set health to 0 and call game state to do stuff
    public void callDeath() {
        health = 0;
        Vars.game.bossDeath();
    }

    public void takeBulletDamage(PlayerBullet bullet) {
        GameStats.damage(bullet.getDamage() * vulnerability);
    }

    @Override
    public void draw() {
        type.draw(this);
    }

    @Override
    public void added(EntityGroup group) {
        type.spawned(this);
    }

    @Override
    public void removed(EntityGroup group) {
        CPools.free(this);
        type.death(this);
    }

    public BossType type() {
        return type;
    }

    public void type(BossType type) {
        this.type = type;
    }

    public static BossEntity spawn(Cons<BossEntity> entity) {
        BossEntity ent = CPools.obtain(BossEntity.class, BossEntity::new);

        ent.add();
        return ent;
    }
}
