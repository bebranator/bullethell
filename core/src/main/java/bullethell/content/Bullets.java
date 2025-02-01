package bullethell.content;

import bullethell.core.Core;
import bullethell.type.BulletType;

import java.nio.file.attribute.UserPrincipal;

public class Bullets {
    public static BulletType testBullet;

    public static void init() {
        testBullet = new BulletType("test") {{
            sprite = Core.atlas.findRegion("pixel");
        }};
    }
}
