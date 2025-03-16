package bullethell.content;

import bullethell.assets.Assets;
import bullethell.core.Core;
import bullethell.entity.type.Bullet;
import bullethell.graphics.Fill;
import bullethell.type.BulletType;
import com.badlogic.gdx.assets.AssetLoaderParameters;

public class Bullets extends Content {
    public static BulletType testBullet, transparent, blueSmall;

    @Override
    public void init() {
        testBullet = new BulletType("test") {{
            sprite = Assets.white;
        }};

        blueSmall = new BulletType("blue-small");

        transparent = new BulletType("red-big-transparent");
    }
}
