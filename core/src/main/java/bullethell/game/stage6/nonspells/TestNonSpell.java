package bullethell.game.stage6.nonspells;

import bullethell.content.Bullets;
import bullethell.core.Core;
import bullethell.entity.EntityUpdater;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Laser;
import bullethell.func.Cons;
import bullethell.game.Attack;
import bullethell.log.Log;
import bullethell.utils.Interval;
import bullethell.utils.Tmp;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;

import static bullethell.core.Vars.*;
import static com.badlogic.gdx.math.MathUtils.*;

public class TestNonSpell extends Attack {
    private Interval bulletSpawn = new Interval(1);

    public TestNonSpell() {
        this.lifetime = 5 * 60;
    }

    EntityUpdater<Bullet> updater = (bullet) -> {
        float time = bullet.time();

        bullet.setSize(Math.max(190 - time * 1.2f, 5));
        bullet.drawSize(bullet.getSize() + 12);
    };

    @Override
    protected void update() {
        if(!bulletSpawn.get(16)) return;
        sounds.boom02();
        // size * 2 = 120
        float randPoint = random() * (arena.world.width - arena.world.x);

        Bullet.spawn(e -> {
            e.lifetime = 300;
            e.type = Bullets.transparent;
            e.updater = updater;
            e.set(arena.world.x + randPoint, 800);
//            e.velocity().set(0, -1);
//            e.speed = 4;
            e.params().linear(0, -4);
        });
        spawnBullet(arena.world.x + randPoint);
    }

    final int amount = 27;
    public void spawnBullet(float b) {
        float rand_angle = random() * 360;
        // launch 8 small bullets
        for(int i = 0; i < amount; i++) {
            int finalI = i;
            Bullet.spawn(y -> {
                y.set(b, 800);
                y.setSize(2);
                y.drawSize(12);
//                y.params().velocity.set(4, 0).rotateDeg(finalI * 360f / amount);
                y.lifetime = 900;
                y.type = Bullets.blueSmall;
            });
        }
    }
}
