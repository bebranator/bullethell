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
        super("bullet_circle",  5000000, SC_NO_BONUS_BURN | SC_NO_DEATH_BONUS_LOSS);
        lifetime = 5 * 60;
    }

    final float summonInterval = byDifficulty(16, 12, 8, 6, 6);
    final int summon = byDifficulty(4, 6, 8, 10, 10);
    float angle;

    @Override
    protected void update() {
        if(!interval.get(summonInterval)) return;
        angle = sind(time * .5f) * 160f + cosd(time * 1.25f) * 120f;

        summonBullets(ax() + width() / 2, ay() + height() / 2);
    }

    public void summonBullets(float x, float y) {
        for(int i = 0; i < summon; i++) {
            int c = i;

            Bullet.spawn(e -> {
                int sign = ((c % 2) == 0) ? 1 : -1;
                e.setSize(2);
                e.drawSize = 12;
//                e.velocity().rotateDeg((c * 360f / summon) + angle);
//                e.mover.speed = () -> 2;
//                e.mover.rotationRate = () -> sign * 30f / e.time();
//                e.mover.targetPoint(ax() + width() / 2, ay() + height() / 2);
//                e.mover.targetRate = .6f;
//                e.mover.rotationDeg(c * 360f / summon + angle);
//                e.mover.speed(b -> 6 * (sind(e.time()) + 0.5f) / 2).rotation((bullet) -> sign * 1f / e.time());
                e.params().linear(6, 0).rotateVelocity(c * 360f / summon + angle);
            }, Bullets.blueSmall, x, y);
        }
    }
}
