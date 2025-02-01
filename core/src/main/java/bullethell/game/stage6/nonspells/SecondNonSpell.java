package bullethell.game.stage6.nonspells;

import bullethell.content.Bullets;
import bullethell.entity.type.Bullet;
import bullethell.game.Attack;
import bullethell.type.BulletType;
import bullethell.utils.Tmp;
import com.badlogic.gdx.utils.Array;

public class SecondNonSpell extends Attack {
    private Array<Bullet> badStars = new Array<>();

    @Override
    protected void update() {
        if(badStars.isEmpty()) {
            for (int i = 0; i < 10; i++) {
                badStars.add(Bullet.spawn(e -> {
                    e.lifetime = 300;
                    e.drawSize = 12;
                    e.type = Bullets.testBullet;
                    e.setSize(12);
                    e.move.linearMoveTo(Tmp.v21.set(400, 400), 0.9f);
                    e.set(600, 600);
                }));
            }
        }
    }
}
