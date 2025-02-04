package bullethell.content;

import bullethell.core.Core;
import bullethell.entity.type.Bullet;
import bullethell.graphics.Fill;
import bullethell.type.BulletType;
import com.badlogic.gdx.graphics.Color;

import java.nio.file.attribute.UserPrincipal;

public class Bullets {
    public static BulletType testBullet, transparent;

    public static void init() {
        testBullet = new BulletType("test") {{
            sprite = Core.atlas.findRegion("pixel");
        }};

        transparent = new BulletType("transparent") {
            @Override
            public void draw(Bullet bullet) {
                Fill.color(Color.GRAY);
                Fill.alpha(.6f);
                Fill.filled();
                Fill.circle(bullet.getX(), bullet.getY(), bullet.drawSize);
                Fill.alpha();
                Fill.color();
            }
        };
    }
}
