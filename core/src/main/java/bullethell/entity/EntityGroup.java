package bullethell.entity;

import bullethell.entity.trait.Drawc;
import bullethell.entity.trait.Entityc;
import bullethell.func.Cons;
import com.badlogic.gdx.utils.Array;

// todo: entities
public class EntityGroup<T extends Entityc> {
    private final Array<T> pending, removal, current;

    public EntityGroup() {
        pending = new Array<>();
        removal = new Array<>();
        current = new Array<>();
    }

    public void add(T entity) {
        pending.add(entity);
        entity.setGroup(this);
    }
    public void remove(T entity) {
        removal.add(entity);
    }
    public void forEach(Cons<T> e) {
        current.forEach(e::get);
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

    public void draw() {
        current.forEach(Drawc::draw);
    }
}
