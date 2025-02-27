package bullethell.entity;

import bullethell.entity.trait.Entityc;

public class Entities {
    public <T extends Entityc> EntityGroup<T> getGroup(Class<T> type) {
        return new EntityGroup<>(type, false);
    }
    public <T extends Entityc> EntityGroup<T> getGroup(Class<T> type, boolean solid) {
        return new EntityGroup<>(type, solid);
    }
}
