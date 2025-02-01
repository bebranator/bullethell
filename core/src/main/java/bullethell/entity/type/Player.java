package bullethell.entity.type;

import bullethell.content.Bullets;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.Arena;
import bullethell.entity.Collisions;
import bullethell.entity.EntityGroup;
import bullethell.entity.trait.CircleHitboxc;
import bullethell.entity.trait.Solidc;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.module.Fonts;
import bullethell.type.BulletType;
import bullethell.utils.Interval;
import bullethell.utils.Tmp;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;

import static bullethell.core.Core.cinput;
import static bullethell.module.Bindings.*;
import static bullethell.core.Vars.*;

public class Player extends BaseCircleHitboxEntity {
    private Interval shotInterval = new Interval();
    public boolean invuln = false;
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

    public Player() {
        setSize(6);
    }

    Sound death = Core.audio.newSound(Core.files.internal("sound/se_pldead00.wav"));
    public void death(Bullet ignored) {
        death.play();
        invuln = true;
        set(arena.world.width / 2 + arena.world.x, arena.world.height / 8 + arena.world.y);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                invuln = false;
            }
        }, 3f);
    }

    // bad lmao
    public void deathLaser(Laser ignored) {
//        this.death(null);
    }

    @Override
    public void update() {
        super.update();

        if(!invuln) {
            Collisions.circleWCircle(Vars.enemyBullets, this, this::death);
            Collisions.playerLaser(this, this::deathLaser);
        }
        // horizontal is faster. this is intended
        float x = axis(moveLeft, moveRight) * 6;
        float y = axis(moveDown, moveUp) * 6;

        velocity().set(x, y).scl(Core.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? .75f : 1);
        // shot bullets from both sides

        // if we hold shot key and interval right

        if(cinput.isPressed(Input.Keys.Z)) {
            if(!shotInterval.get(2)) return;

            Bullet.playerBullet((e) -> {
                e.setSize(3);
                e.drawSize = 3;
                e.type = Bullets.testBullet;
                e.lifetime = 300;
                e.velocity().set(0, 25);
            }, getX() + 20, getY() - 20);
            Bullet.playerBullet((e) -> {
                e.setSize(3);
                e.drawSize = 3;
                e.type = Bullets.testBullet;
                e.velocity().set(0, 25);
                e.lifetime = 300;
            }, getX() - 20, getY() - 20);
        }
    }

    @Override
    public void draw() {
        Draw.color();
        Fill.filled();
        Fill.circle(getX(), getY(), getSize());
//        Fill.line();
//        Draw.textMode();
//        Draw.text(Fonts.kelly12, "x=" + getX() + "; y=" + getY(), getX(), getY());
//        Draw.textEnd();
    }
}
