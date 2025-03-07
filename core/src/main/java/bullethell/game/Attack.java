package bullethell.game;

import bullethell.core.Vars;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.EnemyEntity;
import bullethell.entity.type.Player;
import bullethell.func.Cons;
import bullethell.log.Log;
import bullethell.type.BulletType;
import bullethell.type.EnemyType;
import bullethell.utils.CPools;
import bullethell.utils.Time;
import bullethell.utils.Tmp;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

// current attack wave
// spell cards are just big waves
public class Attack {
    public float time = 0, lifetime;

    public void superUpdate() {
        time = MathUtils.clamp(time + Time.delta, 0, lifetime);
        update();
    }

    protected void update() {
    }

    public void draw() {}
    public void begin() {}
    public void reset() {}

    public void end() {
//        lifetime = 0; // why tf did i
        time = 0;
    }

    // check if we should stop current attack
    public boolean isEnd() {
        return time >= lifetime;
    }

    public void schedule(Runnable runnable, float period, float interval, int count) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                runnable.run();
            }
        }, period, interval, count);
    }

    public float byDifficulty(float easy, float normal, float hard, float expert, float lunatic) {
        return switch (Vars.game.difficulty) {
            case easy: yield easy;
            case normal: yield normal;
            case hard: yield hard;
            case expert: yield expert;
            case lunatic: yield lunatic;
        };
    }
    public int byDifficulty(int easy, int normal, int hard, int expert, int lunatic) {
        return switch (Vars.game.difficulty) {
            case easy: yield easy;
            case normal: yield normal;
            case hard: yield hard;
            case expert: yield expert;
            case lunatic: yield lunatic;
        };
    }
    // arena width
    public float width() {
        return Vars.arena.world.width;
    }

    public float height() {
        return Vars.arena.world.height;
    }

    public float ax() {
        return Vars.arena.world.x;
    }
    public float ay() {
        return Vars.arena.world.y;
    }

    public Player player() {
        return Vars.player;
    }
    public Bullet aimedLinearBullet(Cons<Bullet> spawn, BulletType type, float x, float y, float speed) {
        Vector2 dir = Tmp.v21;

        dir.set(player().getX() - x, player().getY() - y).setLength(speed);

        return Bullet.spawn((e) -> {
            e.params().linear(dir);
            spawn.get(e);
        }, type, x, y);
    }
    public Bullet bullet(Cons<Bullet> spawn, BulletType type, float x, float y, float velX, float velY) {
        return Bullet.spawn((e) -> {
            e.params().linear(velX, velY);
            spawn.get(e);
        }, type, x, y);
    }

    public EnemyEntity spawnEnemy(EnemyType type, Cons<EnemyEntity> cons) {
        return EnemyEntity.spawn(type, cons);
    }

    public String debug() {
        return "";
    }
}
