package bullethell.entity;

import bullethell.core.Vars;
import bullethell.entity.trait.CircleHitboxc;
import bullethell.entity.type.Laser;
import bullethell.func.Cons;
import com.badlogic.gdx.math.Circle;

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

    // do collision with laser
    // were some problems with name "LaserEntity"
    public static void playerLaser(CircleHitboxc player, Cons<Laser> collision) {
        Vars.lasers.forEach(e -> {
            if(e.hitbox.circleCollision(player.hitbox())) collision.get(e);
        });
    }
}
