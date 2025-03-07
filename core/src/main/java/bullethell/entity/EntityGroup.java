package bullethell.entity;

import bullethell.core.Vars;
import bullethell.entity.trait.Entityc;
import bullethell.entity.trait.Solidc;
import bullethell.func.Cons;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

// todo: entities
public class EntityGroup<T extends Entityc> {
    private final Array<T> pending, removal, current;
    private Class<T> entityType;
    private boolean solid;

    public EntityGroup(Class<T> entityType, boolean solid) {
        pending = new Array<>();
        removal = new Array<>();
        current = new Array<>();
        this.entityType = entityType;

        this.solid = solid;
    }

    public void clear() {
        for(Entityc entity : current) {
            entity.removed(this);
        }
        current.clear();
    }

    public boolean empty() {
        return current.isEmpty();
    }

    public void add(T entity) {
        pending.add(entity);
    }
    public void remove(T entity) {
        removal.add(entity);
    }
    public void forEach(Cons<T> e) {
        current.forEach(e::get);
    }
    public Array<T> entities() {
        return current;
    }

    public void update() {
        // update pending, removal
        // do entity update;

        pending.forEach((e) -> e.added(this));
        current.addAll(pending);
        pending.clear();

        removal.forEach(e -> e.removed(this));
        // was a stupid mistake "pending.removeAll(removal, false)"
        current.removeAll(removal, false);
        removal.clear();

        current.forEach(Entityc::update);
    }

    Rectangle viewport = Vars.arena.drawViewport;
    public void draw() {
        if(solid) {
            current.forEach(e -> {
                Solidc solid = (Solidc) e;

                if(solid.intersect(viewport)) e.draw();
            });
        }else
            // check if coordinates inside viewport
            current.forEach(e -> {
                if(viewport.contains(e.getX(), e.getY())) e.draw();
            });
    }
}
