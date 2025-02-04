package bullethell.type;

import bullethell.entity.type.BossEntity;
import bullethell.graphics.Fill;
import com.badlogic.gdx.graphics.Color;

// defines basic stuff like hitbox size, drawsize and etc
public class BossType {
    public float hitboxSize = 40, drawSize = 36;

    public final String name;

    public BossType(String name) {
        this.name = name;
    }

    public void spawned(BossEntity entity) {
        entity.setSize(hitboxSize);
    }

    public void death(BossEntity entity) {

    }

    public void draw(BossEntity boss) {
        Fill.filled();
        Fill.color(Color.FIREBRICK);
        Fill.circle(boss.getX(), boss.getY(), boss.hitbox().radius);
        Fill.color();
    }
}
