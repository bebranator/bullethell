package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.EntityGroup;

public class PlayerBullet extends Bullet {
    public float damage = 10;

    @Override
    public EntityGroup targetGroup() {
        return Vars.playerBullets;
    }

    @Override
    public void outOfBounds() {
        remove();
    }

    public float getDamage() {
        return damage;
    }
}
