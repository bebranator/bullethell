package bullethell.game.stage6.spells;

import bullethell.content.Bullets;
import bullethell.entity.type.BossEntity;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Player;
import bullethell.func.Cons;
import bullethell.game.spell.SpellCard;
import bullethell.type.BulletType;
import bullethell.utils.Interval;
import com.badlogic.gdx.math.MathUtils;

public class PetaFlare extends SpellCard {
    private Interval shotInterval = new Interval(1);

    public PetaFlare() {
        super("peta_flare", 5000000, SC_NO_TIMEOUT_BONUS);
    }

    final float initialSize = 160;
    final float decreaseCoefficient = .4f; // by time
    final float minimalSize = 40;
    Cons<Bullet> updater = (bullet) -> {
        bullet.setSize(Math.max(160 - bullet.time() * decreaseCoefficient, minimalSize));

        bullet.drawSize = bullet.getSize() + 12;
    };

    @Override
    protected void update() {
        float shotTime = byDifficulty(40, 32, 24, 16, 16);
        int amount = byDifficulty(10, 16, 24, 27, 27);

        if(!shotInterval.get(shotTime)) return;

        float x = MathUtils.random() * (width() - initialSize / 2) + ax() + initialSize / 2;
        float y = ay() + height() - initialSize / 2 + MathUtils.random() * 120;
        shotBullets(x, y, amount);
    }

    public void shotBullets(float x, float y, int amount) {
        Bullet.spawn((e) -> {
            e.updater = updater;
            e.velocity().set(0, -12);
        }, Bullets.transparent, x, y);

        for(int i = 0; i < amount; i++) {
            int tmpI = i;
            Bullet.spawn((e) -> {
                e.setSize(2);
                e.drawSize = 12;
                e.velocity().set(1, 0).rotateDeg(360 * tmpI / 27f);
            }, Bullets.blueSmall, x, y);
        }
    }
}
