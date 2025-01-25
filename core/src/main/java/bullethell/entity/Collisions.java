package bullethell.entity;

import bullethell.core.Vars;
import bullethell.entity.trait.CircleHitboxc;
import bullethell.entity.trait.Solidc;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Player;
import bullethell.func.Cons;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class Collisions {
    static Circle tmp;
    // todo: implement collision checks with circles, rectangles, ellipses
    public static <T extends CircleHitboxc> void circleWCircle(EntityGroup<T> checks, CircleHitboxc target, Cons<T> collision) {
        for(T e : checks.entities()) {
            tmp = e.hitbox();

            if(target.hitbox().overlaps(tmp)) {
                collision.get(e);
                break;
            }
        };
    }

    static Circle h;
    public static <T extends Solidc> void graze(Player target, Cons<Bullet> graze) {
        Vars.enemyBullets.forEach(e -> {
            h = e.grazeBox;
            if(target.hitbox().overlaps(h)) graze.get(e);
        });
    }
}
