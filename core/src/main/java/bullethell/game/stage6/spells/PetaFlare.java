package bullethell.game.stage6.spells;

import bullethell.content.Bullets;
import bullethell.entity.EntityUpdater;
import bullethell.entity.type.BossEntity;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Player;
import bullethell.func.Cons;
import bullethell.game.spell.SpellCard;
import bullethell.type.BulletType;
import bullethell.utils.Interval;
import com.badlogic.gdx.math.MathUtils;

import static bullethell.core.Vars.sounds;

public class PetaFlare extends SpellCard {
    private Interval shotInterval = new Interval(1);

    public PetaFlare() {
        super("peta_flare", 5000000, SC_NO_BONUS_BURN);
        lifetime = 75 * 60; // we live for 75 seconds then timeout
    }

    final float initialSize = 160;
    final float decreaseCoefficient = .7f; // by time
    final float minimalSize = 40;
    EntityUpdater<Bullet> updater = (bullet) -> {
        bullet.setSize(Math.max(160 - bullet.time() * decreaseCoefficient, minimalSize));

        bullet.drawSize(bullet.getSize() + 12);
    };

    @Override
    protected void update() {
        float shotTime = byDifficulty(40, 32, 24, 16, 16);

        if(!shotInterval.get(shotTime)) return;

        int amount = byDifficulty(10, 16, 24, 27, 27);

        float x = MathUtils.random() * (width() - initialSize / 2) + ax() + initialSize / 2;
        float y = ay() + height() - initialSize / 2 + MathUtils.random() * 120;
        shotBullets(x, y, amount);
    }

    public void shotBullets(float x, float y, int amount) {
        sounds.boom02();
        float angle = 0;
        Bullet.spawn((e) -> {
            e.updater = updater;
//            e.velocity().set(0, -1);
//            e.speed = 12;
//            e.mover.direction(0, -1).speed(2);
            e.params().linear(0, -4);
        }, Bullets.transparent, x, y);

        for(int i = 0; i < amount; i++) {
            int tmpI = i;
            Bullet.spawn((e) -> {
                e.setSize(8);
                e.drawSize(12);
//                e.speed = 4;
//                e.velocity().set(1, 0).rotateDeg(360 * tmpI / 16f);
//                e.mover.direction(360 * tmpI / 16f).speed(4);
//                e.params.linear(4, 0).rotateVelocity(360 * tmpI / 16f + angle);
//                e.params.accelerated(4, 0, .2f, 0)
//                    .rotateAcceleration(360 * tmpI / 16f + angle)
//                    .rotateVelocity(360 * tmpI / 16f + angle);
//                e.params().asymptotic(8, 0, 9f, 0, .1f, 0).rotate(360 * tmpI / 16f + angle)
//                    .rotateRetention(360 * tmpI / 16f + angle);
                e.params().towards(.3f, -.3f, player().getX(), player().getY(), .04f, -.04f);
            }, Bullets.blueSmall, x, y);
        }
    }
}
