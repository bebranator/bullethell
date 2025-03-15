package bullethell.entity.type;

import bullethell.core.Vars;
import bullethell.entity.EntityGroup;
import bullethell.entity.EntityUpdater;
import bullethell.entity.trait.Arenac;
import bullethell.entity.trait.Entityc;
import bullethell.func.Cons;
import bullethell.game.GameTime;
import bullethell.movement.Mover;
import bullethell.type.EnemyType;
import bullethell.utils.CPools;
import com.badlogic.gdx.utils.Pool;

public class EnemyEntity extends HealthEntity implements Pool.Poolable, Arenac {
    public Cons<EnemyEntity> onDeath = (e) -> {};
    public Cons<EnemyEntity> outOfBounds = Entityc::remove;
    public EntityUpdater<EnemyEntity> updater = (e) -> {};

    public EnemyType type = null;

    @Override
    public EntityGroup targetGroup() {
        return Vars.enemies;
    }

    @Override
    public void added(EntityGroup group) {
        birthTime = GameTime.time;
        type.spawned(this);
    }

    @Override
    public void removed(EntityGroup group) {
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

    @Override
    public void reset() {
        updater = (e) -> {};

        birthTime = 0;
        params().reset();
        type(null);
    }

    public static EnemyEntity spawn(EnemyType type, Cons<EnemyEntity> spawn) {
        EnemyEntity ent = CPools.obtain(EnemyEntity.class, EnemyEntity::new);

        ent.type(type);
        spawn.get(ent);
        ent.add();

        return ent;
    }

    public static EnemyEntity spawn(EnemyType type, float x, float y, Cons<EnemyEntity> spawn) {
        EnemyEntity ent = CPools.obtain(EnemyEntity.class, EnemyEntity::new);
        ent.type(type);
        ent.set(x, y);
        spawn.get(ent);

        return ent;
    }

    @Override
    public void hitBounds() {

    }

    @Override
    public void outOfBounds() {
        outOfBounds.get(this);
    }
}
