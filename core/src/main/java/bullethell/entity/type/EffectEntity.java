package bullethell.entity.type;

import bullethell.entity.EntityGroup;
import bullethell.entity.trait.BaseEntity;
import bullethell.entity.trait.Scaledc;

public class EffectEntity extends BaseEntity implements Scaledc {
    @Override
    public EntityGroup targetGroup() {
        return null;
    }

    @Override
    public void setSize(float size) {

    }

    @Override
    public float getSize() {
        return 0;
    }
}
