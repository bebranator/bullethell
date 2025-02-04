package bullethell.type;

import bullethell.core.Core;
import bullethell.entity.type.Bullet;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.module.Tex;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BulletType {
    public final String name;

    public TextureRegion sprite;

    public BulletType(String name) {
        this.name = name;
        sprite = Core.atlas.findRegion(name);
    }

    public void draw(Bullet bullet) {
//        Draw.draw(sprite, bullet.getX(), bullet.getY(), bullet.drawSize, bullet.drawSize);
        Fill.alpha();
        Fill.filled();
        Fill.circle(bullet.getX(), bullet.getY(), bullet.drawSize);
    }

    public void spawned(Bullet bullet) {
    }

    public void death(Bullet bullet) {
    }
}
