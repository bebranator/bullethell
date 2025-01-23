package bullethell.entity;

import bullethell.core.Vars;
import bullethell.entity.trait.Solidc;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Player;
import bullethell.func.Cons;
import com.badlogic.gdx.math.Circle;

public class Collisions {
    public static <T extends Solidc> void collision(EntityGroup<T> group, Solidc target, Cons<T> collision) {
        group.forEach(e -> {
            if(target.collidesWith(e)) collision.get(e);
        });
    }

    static Circle h;
    public static <T extends Solidc> void graze(Player target, Cons<Bullet> graze) {
        Vars.enemyBullets.forEach(e -> {
            h = e.grazeBox;
            if(target.hitbox().overlaps(h)) graze.get(e);
        });
    }
}
