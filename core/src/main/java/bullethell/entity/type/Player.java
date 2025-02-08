package bullethell.entity.type;

import bullethell.content.Bullets;
import bullethell.content.Sounds;
import bullethell.core.Core;
import bullethell.core.Events;
import bullethell.core.Vars;
import bullethell.entity.Collisions;
import bullethell.entity.EntityGroup;
import bullethell.game.Ev;
import bullethell.graphics.Draw;
import bullethell.utils.Interval;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

import static bullethell.core.Core.cinput;
import static bullethell.module.Bindings.*;
import static bullethell.core.Vars.*;

public class Player extends BaseCircleHitboxEntity {
    private Interval shotInterval = new Interval();
    public boolean invuln = false;

    public Player() {
        setSize(6);
    }

    public void kill() {
        sounds.playSound(Sounds.death, .5f);
        invuln = true;
        defaultLocation();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                invuln = false;
            }
        }, 3f);
        Events.fire(Ev.PlayerDeathEvent.class);
    }
    public void defaultLocation() {
        set(arena.world.width / 2 + arena.world.x, arena.world.height / 8 + arena.world.y);
    }

    @Override
    public void update() {
        super.update();

        if(!invuln) {
            Collisions.circleWCircle(Vars.enemyBullets, this, (a) -> kill());
            Collisions.playerLaser(this, (a) -> kill());
        }
        float x = axis(moveLeft, moveRight);
        float y = axis(moveDown, moveUp);

        velocity().set(x, y);

        if(cinput.isPressed(Input.Keys.Z)) {
            if(!shotInterval.get(2)) return;

            Bullet.playerBullet((e) -> {
                e.setSize(3);
                e.drawSize = 3;
                e.type = Bullets.testBullet;
                e.velocity().set(0, 1);
                e.speed = 25;
                e.lifetime = 300;
            }, getX() + 20, getY() - 20);

            Bullet.playerBullet((e) -> {
                e.setSize(3);
                e.drawSize = 3;
                e.type = Bullets.testBullet;
                e.velocity().set(0, 1);
                e.speed = 25;
                e.lifetime = 300;
            }, getX() - 20, getY() - 20);
        }
    }

    @Override
    public void draw() {
        Draw.color();
        Draw.fill(Core.atlas.findRegion("blue-small"), getX(), getY(), getSize(), getSize());
    }

    @Override
    public void setY(float y) {
        y = MathUtils.clamp(y, arena.world.y + getSize(), arena.world.y + arena.world.height - getSize());
        super.setY(y);
    }

    @Override
    public void setX(float x) {
        x = MathUtils.clamp(x, arena.world.x + getSize(), arena.world.x + arena.world.width - getSize());
        super.setX(x);
    }

    @Override
    public EntityGroup targetGroup() {
        return playerGroup;
    }
    public boolean focused() {
        return cinput.isPressed(Input.Keys.SHIFT_LEFT);
    }

    @Override
    public float speed() {
        return focused() ? 3 : 6;
    }
}
