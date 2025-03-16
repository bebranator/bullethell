package bullethell.entity.type;

import bullethell.content.PlayerTypes;
import bullethell.assets.Sounds;
import bullethell.core.Events;
import bullethell.entity.Collisions;
import bullethell.entity.EntityGroup;
import bullethell.game.Ev;
import bullethell.game.GameStats;
import bullethell.movement.Mover;
import bullethell.type.PlayerType;
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

    public int axisX;
    public int movement;

    public PlayerType type;

    public Player() {
        params().reset();
        setSize(6);
        type(PlayerTypes.seija);
        drawSize = 24;
    }

    public void kill() {
        sounds.playSound(Sounds.death, .5f);
        defaultLocation();
        invuln = true;

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                invuln = false;
            }
        }, 3f);

        Events.fire(new Ev.PlayerDeathEvent());
        GameStats.deaths++;
    }
    public void defaultLocation() {
        set(arena.world.width / 2 + arena.world.x, arena.world.height / 8 + arena.world.y);
    }

    @Override
    public void update() {
        super.update();

        if(!invuln) {
//            Collisions.circleWCircle(Vars.enemyBullets, this, (a) -> kill());
            Collisions.playerLaser(this, (a) -> kill());
            Collisions.player((e) -> kill());
        }
        int x = axis(moveLeft, moveRight);
        int y = axis(moveDown, moveUp);

        if(axisX != x) type.changeAxisX(x);

        axisX = x;
        params().linear(x * speed(), y * speed());

        Mover.update(this, params());
        type.update(this);
    }

    @Override
    public void draw() {
        type.draw(this);
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
    public float speed() {
        return focused() ? type.focusSpeed : type.speed;
    }

    public void type(PlayerType type) {
        this.type = type;
    }
    public PlayerType type() {
        return type;
    }
}
