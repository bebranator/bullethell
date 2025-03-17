package bullethell.entity.effect;

import bullethell.entity.trait.Interpc;
import bullethell.entity.trait.Movec;
import bullethell.entity.type.EffectEntity;
import bullethell.func.Cons;
import bullethell.utils.CPools;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

public class Effects {
    private static final Container container = new Container();
    private static Array<Effect> effects = new Array<>();

    private static Provider provider = (effect, color, x, y, rotation, data) -> {
        EffectEntity entity = CPools.obtain(EffectEntity.class, EffectEntity::new);
        entity.effect = effect;
        entity.color = color;
        entity.rotation = rotation;
        entity.data = data;
        entity.set(x, y);
        entity.add();
    };

    public static void drawEffect(Effect render, Color color, float life, float rotation, float x, float y, Object data){
        container.set(render.id, color, life, render.lifetime, rotation, x, y, data);
        render.draw.draw(container);
    }

//    public static Effect getEffect(int id){
//        if(id >= effects.size || id < 0)
//            throw new IllegalArgumentException("The effect with ID \"" + id + "\" does not exist!");
//        return effects.get(id);
//    }

    public static Array<Effect> defined(){
        return effects;
    }

    public static void effect(Effect effect, float x, float y, float rotation){
        provider.makeEffect(effect, Color.WHITE, x, y, rotation, null);
    }

    public static void effect(Effect effect, float x, float y){
        effect(effect, x, y, 0);
    }

    public static void effect(Effect effect, Color color, float x, float y){
        provider.makeEffect(effect, color, x, y, 0f, null);
    }

    public static void effect(Effect effect, Movec loc){
        provider.makeEffect(effect, Color.WHITE, loc.getX(), loc.getY(), 0f, null);
    }

    public static void effect(Effect effect, Color color, float x, float y, float rotation){
        provider.makeEffect(effect, color, x, y, rotation, null);
    }

    public static void effect(Effect effect, Color color, float x, float y, float rotation, Object data){
        provider.makeEffect(effect, color, x, y, rotation, data);
    }

    public static void effect(Effect effect, float x, float y, float rotation, Object data){
        provider.makeEffect(effect, Color.WHITE, x, y, rotation, data);
    }

    public static class Effect{
        private static int lastid = 0;
        public final int id;
        public final Drawer draw;
        public final float lifetime;
//        /** Clip size. */
//        public float size;

        public Effect(float life, Drawer draw){
            this.id = lastid++;
            this.lifetime = life;
            this.draw = draw;
            effects.add(this);
        }

        public void at(float x, float y) {
            Effects.effect(this, x, y);
        }
        public void at(float x, float y, Color color) {
            Effects.effect(this, color, x, y);
        }
    }

    public static class Container implements Interpc {
        public float x, y, time, lifetime, rotation;
        public Color color;
        public int id;
        public Object data;
        private Container cont;

        public void set(int id, Color color, float life, float lifetime, float rotation, float x, float y, Object data){
            this.x = x;
            this.y = y;
            this.color = color;
            this.time = life;
            this.lifetime = lifetime;
            this.id = id;
            this.rotation = rotation;
            this.data = data;
        }

        public void scaled(float lifetime, Cons<Container> cons){
            if(cont == null) cont = new Container();
            if(time <= lifetime){
                cont.set(id, color, time, lifetime, rotation, x, y, data);
                cons.get(cont);
            }
        }

        @Override
        public float fin(){
            return time / lifetime;
        }
    }

    public interface Provider{
        void makeEffect(Effect effect, Color color, float x, float y, float rotation, Object data);
    }

    public interface Drawer{
        void draw(Container effect);
    }
}
