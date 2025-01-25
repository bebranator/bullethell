package bullethell.entity;

import bullethell.entity.trait.Entityc;

public class Entities {
    public <T extends Entityc> EntityGroup<T> getGroup(Class<T> entityType) {
        return new EntityGroup<>(entityType);
    }
}
