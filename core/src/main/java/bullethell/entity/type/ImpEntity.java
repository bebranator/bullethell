package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.EntityGroup;

public class ImpEntity extends HealthEntity {
    @Override
    public EntityGroup targetGroup() {
        return Vars.healthEntities;
    }
}
