package bullethell.entity;

import bullethell.entity.trait.Entityc;

public interface EntityUpdater<T extends Entityc> {
    void update(T entity);
}
