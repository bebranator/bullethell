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
import bullethell.utils.Tmp;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SecondNonSpell extends Attack {
    private Laser laser;
    private Matrix4 small = Core.camera.combined.cpy().scl(.25f, .25f, .25f);

    public SecondNonSpell() {
        lifetime = 1200;
    }

    @Override
    protected void update() {
        if(laser == null) {
            laser = Laser.spawn(e -> {
                e.lifetime = -1;
                e.set(400, 400);
                e.setBounds(20, 400);
                e.center();
            });
        }
        laser.rotate(0.6f);
    }

    @Override
    public void end() {
        laser = null;
    }

    final Vector2 b = new Vector2();

    @Override
    public void draw() {
        if(laser == null) return;
        CRectangle rect = laser.hitbox;
        Player p = Vars.player;

        Fill.proj(small);
        Fill.color();
        Fill.filled();
        Fill.rect(rect.x, rect.y, rect.w, rect.h);
        b.set(p.getX(), p.getY());

        rect.transform(b, rect.rotation);
        if(rect.circleCollision(p.hitbox())) Fill.color(Color.GREEN);
        Fill.circle(b.x, b.y, 6);
        Fill.color();
        Fill.proj();
//        Fill.rect(500, 500, 10, 10, 0);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
