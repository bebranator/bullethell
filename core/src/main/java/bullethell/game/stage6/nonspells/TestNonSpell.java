package bullethell.game.stage6.nonspells;

import bullethell.content.Bullets;
import bullethell.entity.type.Bullet;
import bullethell.func.Cons;
import bullethell.game.Attack;
import bullethell.log.Log;
import bullethell.utils.Interval;
import com.badlogic.gdx.math.MathUtils;

import static bullethell.core.Vars.*;
import static com.badlogic.gdx.math.MathUtils.*;

public class TestNonSpell extends Attack {
    private Interval bulletSpawn = new Interval(1);

    public TestNonSpell() {
        this.lifetime = 600;
    }

    Cons<Bullet> updater = (bullet) -> {
        float time = bullet.time();

        bullet.setSize(Math.max(120 - time * 0.3f, 16));
        bullet.drawSize = bullet.getSize() + 12;
    };

    @Override
    protected void update() {
        if(!bulletSpawn.get(16)) return;
        // size * 2 = 120
        float randPoint = random() * (arena.world.width - arena.world.x - 120);

        Bullet.spawn(e -> {
            e.lifetime = 300;
            e.type = Bullets.testBullet;
            e.updater = updater;
            e.set(arena.world.x + randPoint, 800);
            e.velocity().set(0, -4);
        });
        spawnBullet(arena.world.x + randPoint);
    }

    final int amount = 18;
    public void spawnBullet(float b) {
        float rand_angle = random() * 24;
        // launch 8 small bullets
        for(int i = 0; i < amount; i++) {
            int finalI = i;
            Bullet.spawn(y -> {
                y.set(b, 800);
                y.setSize(2);
                y.drawSize = 12;
                y.velocity().set(cosDeg(finalI * 360f/amount + rand_angle) * 4, sinDeg(finalI * 360f/amount + rand_angle) * 4);
                y.lifetime = 300;
                y.type = Bullets.testBullet;
            });
        }
    }
}
