package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.EntityGroup;
import bullethell.entity.effect.Effects;
import bullethell.entity.trait.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Pool;

public class EffectEntity extends BaseEntity implements Timec, Pool.Poolable {
    public Effects.Effect effect;
    private Movec parent;

    public Color color;
    public float rotation, offX, offY;
    private float time;

    public Object data;

    @Override
    public void update() {
        if(effect == null){
            remove();
            return;
        }

        super.update();
        updateTime();
        if(parent != null){
            setX(parent.getX() + offX);
            setY(parent.getY() + offY);
        }
    }

    @Override
    public void draw() {
        Effects.drawEffect(effect, color, time, rotation, getX(), getY(), data);
    }

    @Override
    public EntityGroup targetGroup() {
        return Vars.effects;
    }

    @Override
    public float lifetime() {
        return effect.lifetime;
    }

    @Override
    public float time() {
        return time;
    }

    @Override
    public void time(float time) {
        this.time = time;
    }

    @Override
    public void reset() {
        effect = null;
        time = 0;
        birthTime = 0;
        rotation = 0;
        drawSize = 1;
        color = Color.WHITE;
        data = null;
        parent(null);

        params().reset();

        set(0, 0);
    }
    public void parent(Movec parent){
        this.parent = parent;
        if(parent != null) {
            offX = getX() - parent.getX();
            offY = getY() - parent.getY();
        }else {
            offX = 0;
            offY = 0;
        }
    }

    public Movec parent() {
        return parent;
    }
}
