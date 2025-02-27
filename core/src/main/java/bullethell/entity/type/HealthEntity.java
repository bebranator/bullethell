package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.Collisions;
import bullethell.entity.EntityGroup;
import bullethell.entity.trait.Healthc;
import com.badlogic.gdx.math.MathUtils;

public abstract class HealthEntity extends BaseCircleHitboxEntity implements Healthc {
    public float health = maxHealth();
    public float maxHealth = 600;
    public float damageScale = 1;

    @Override
    public void update() {
        super.update();
        Collisions.playerBullets(this, this::hit);
        Collisions.player(this, this::bashPlayer);
    }

    public void bashPlayer() {
        Vars.player.kill();
    }

    public void hit(PlayerBullet bullet) {
        takeDamage(bullet.damage);
    }

    // used for default spawning
    @Override
    public void ranOutOfHealth() {

    }

    @Override
    public float maxHealth() {
        return maxHealth;
    }

    @Override
    public void maxHealth(float value) {
        maxHealth = value;
    }

    @Override
    public float health() {
        return health;
    }

    @Override
    public void health(float value) {
        this.health = value;
    }

    @Override
    public float damageScale() {
        return damageScale;
    }

    @Override
    public void damageScale(float value) {
        damageScale = value;
    }
}
