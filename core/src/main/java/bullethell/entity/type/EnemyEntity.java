package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.EntityGroup;
import bullethell.entity.EntityUpdater;
import bullethell.func.Cons;
import bullethell.movement.Mover;
import bullethell.type.EnemyType;
import bullethell.utils.CPools;
import com.badlogic.gdx.utils.Pool;

public class EnemyEntity extends HealthEntity implements Pool.Poolable {
    public Cons<EnemyEntity> onDeath = (e) -> {};
    public EntityUpdater<EnemyEntity> updater = (e) -> {};

    public EnemyType type = null;

    @Override
    public EntityGroup targetGroup() {
        return Vars.enemies;
    }

    @Override
    public void added(EntityGroup group) {
        super.added(group);
        type.spawned(this);
    }

    @Override
    public void removed(EntityGroup group) {
        super.removed(group);
        CPools.free(this);
    }

    @Override
    public void update() {
        super.update();
        updater.update(this);
        Mover.update(this, params());
    }

    @Override
    public void ranOutOfHealth() {
        super.ranOutOfHealth();
        onDeath.get(this);
    }

    @Override
    public void draw() {
        type.draw(this);
    }

    public EnemyType type() {
        return type;
    }

    public void type(EnemyType type) {
        this.type = type;
    }

    public static EnemyEntity spawn(EnemyType type, Cons<EnemyEntity> spawn) {
        EnemyEntity ent = CPools.obtain(EnemyEntity.class, EnemyEntity::new);

        ent.type(type);
        spawn.get(ent);
        ent.add();

        return ent;
    }

    @Override
    public void reset() {
        updater = (e) -> {};
        type(null);
    }
}
