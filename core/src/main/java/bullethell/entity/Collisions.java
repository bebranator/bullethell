package bullethell.entity;

import bullethell.core.Vars;
import bullethell.entity.trait.CircleHitboxc;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Laser;
import bullethell.entity.type.Player;
import bullethell.entity.type.PlayerBullet;
import bullethell.func.Cons;
import com.badlogic.gdx.math.Circle;

import static bullethell.core.Vars.*;

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

    public static void player(CircleHitboxc hitbox, Runnable hit) {
        if(player.hitbox().contains(hitbox.hitbox())) hit.run();
    }

    public static void player(Cons<Bullet> hit) {
        for(Bullet bullet : enemyBullets.entities()) {
            if(!bullet.enabled) continue;

            if(player.hitbox().overlaps(bullet.hitbox())) {
                hit.get(bullet);
                break;
            }
        }
    }

    public static void playerBullets(CircleHitboxc hitbox, Cons<PlayerBullet> collide) {
        for(PlayerBullet bullet : playerBullets.entities()) {
            if(bullet.hitbox().contains(hitbox.hitbox())) {
                collide.get(bullet);
                break;
            }
        }
    }

    // do collision with laser
    // were some problems with name "LaserEntity"
    public static void playerLaser(CircleHitboxc player, Cons<Laser> collision) {
        lasers.forEach(e -> {
            if(e.hitbox.circleCollision(player.hitbox())) collision.get(e);
        });
    }
}
