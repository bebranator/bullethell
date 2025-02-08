package bullethell.entity.type;

import bullethell.func.Floats;
import bullethell.game.GameTime;
import bullethell.type.BulletType;

public class BulletMover {
    public Floats speed = () -> 0f, rotationRate = () -> 0f;
    public float originalSpeed;
    public Bullet bullet;

    public void begin(Bullet bullet) {
        this.bullet = bullet;
        originalSpeed = bullet.speed;
    }

    public void update() {
        // doesn't grow that much
        bullet.speed = originalSpeed + this.speed.get();

        bullet.velocity().rotateDeg(rotationRate.get() * GameTime.delta);
    }

    public void reset() {
        speed = () -> 0f;
        rotationRate = () -> 0f;
    }
}
