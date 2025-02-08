package bullethell.entity.trait;

import bullethell.entity.EntityGroup;

// basic entity should have: position, rendering, updating
public interface Entityc extends Movec, Drawc {
    void update();

    default void added(EntityGroup group) {}

    default void removed(EntityGroup group) {}

    default void remove() {
        if(getGroup() != null) {
            getGroup().remove(this);
        }
        setGroup(null);
    }

    default void add() {
        EntityGroup target = targetGroup();

        target.add(this);
    }
    float birthTime();

    // not null
    EntityGroup targetGroup();

    void setGroup(EntityGroup group);

    EntityGroup getGroup();
}
