package bullethell.game.stage6.spells;

import bullethell.content.Bullets;
import bullethell.entity.type.Bullet;
import bullethell.game.spell.SpellCard;
import bullethell.type.BulletType;
import bullethell.utils.Interval;
import com.badlogic.gdx.math.MathUtils;

public class BulletCircle extends SpellCard {
    private Interval interval = new Interval();

    public BulletCircle() {
        super("bullet_circle", 3000000, 0);
        lifetime = 10 * 60;
    }

    final float summonInterval = byDifficulty(10, 8, 6, 4, 4);
    final int summon = byDifficulty(4, 6, 8, 10, 10);
    float angle;

    @Override
    protected void update() {
        if(!interval.get(summonInterval)) return;
        angle = MathUtils.sin(time * .5f) * 160f + MathUtils.cosDeg(time) * 120f;

        summonBullets(ax() + width() / 2, ay() + height() / 2);
    }

    public void summonBullets(float x, float y) {
        for(int i = 0; i < summon; i++) {
            int c = i;

            Bullet.spawn(e -> {
                int sign = ((c % 2) == 0) ? 1 : -1;
                e.setSize(2);
                e.drawSize = 12;
                e.velocity().rotateDeg((c * 360f / summon) + angle);
                e.mover.speed = () -> .06f * e.time();
                e.mover.rotationRate = () -> sign * 60f / e.time();
            }, Bullets.blueSmall, x, y);
        }
    }
}
