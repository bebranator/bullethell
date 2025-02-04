package bullethell.content;

import bullethell.core.Core;
import bullethell.entity.type.Bullet;
import bullethell.graphics.Fill;
import bullethell.type.BulletType;

public class Bullets {
    public static BulletType testBullet, transparent, blueSmall;

    public static void init() {
        testBullet = new BulletType("test") {{
            sprite = Core.atlas.findRegion("pixel");
        }};

        blueSmall = new BulletType("blue-small");

        transparent = new BulletType("red-big-transparent");
    }
}
