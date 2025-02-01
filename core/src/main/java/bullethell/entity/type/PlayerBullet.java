package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.EntityGroup;

public class PlayerBullet extends Bullet {
    @Override
    public EntityGroup targetGroup() {
        return Vars.playerBullets;
    }

    @Override
    public void outOfBounds() {
        remove();
    }
}
