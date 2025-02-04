package bullethell.game.stage6.nonspells;

import bullethell.content.Bullets;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Laser;
import bullethell.entity.type.Player;
import bullethell.game.Attack;
import bullethell.graphics.Fill;
import bullethell.type.BulletType;
import bullethell.utils.CRectangle;
import bullethell.utils.Interval;
import bullethell.utils.Tmp;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import static bullethell.core.Vars.arena;

public class SecondNonSpell extends Attack {
    private Interval wait = new Interval(1);
    public SecondNonSpell() {
        lifetime = 1200;
    }

    int amount = 7;
    float width = 20;
    float mod = arena.world.width / amount;
    @Override
    protected void update() {
    }

    @Override
    public void end() {
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
