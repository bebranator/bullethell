package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.EntityGroup;
import bullethell.entity.trait.BaseEntity;
import bullethell.func.Cons;
import bullethell.type.BossType;
import bullethell.utils.CPools;

// actually i dont think we need to change hitbox size for boss
public class BossEntity extends BaseCircleHitboxEntity {
    private BossType type;

    @Override
    public EntityGroup targetGroup() {
        return Vars.bossGroup;
    }

    @Override
    public void draw() {
        type.draw(this);
    }

    @Override
    public void added(EntityGroup group) {
        type.spawned(this);
    }

    @Override
    public void removed(EntityGroup group) {
        CPools.free(this);
        type.death(this);
    }

    public BossType type() {
        return type;
    }

    public void type(BossType type) {
        this.type = type;
    }

    public static BossEntity spawn(Cons<BossEntity> entity) {
        BossEntity ent = CPools.obtain(BossEntity.class, BossEntity::new);

        ent.add();
        return ent;
    }
}
