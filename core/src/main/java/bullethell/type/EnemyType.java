package bullethell.type;

import bullethell.entity.type.EnemyEntity;
import bullethell.graphics.Fill;

public class EnemyType {
    public final String name;

    public float health = 600, size = 8, drawSize = 8, vulnerability = 1;

    public EnemyType(String name) {
        this.name = name;
        // todo: texture atlas
    }

    public void spawned(EnemyEntity entity) {
        entity.maxHealth(health);
        entity.health(health);
        entity.setSize(size);
        entity.drawSize(drawSize);
    }
    public void draw(EnemyEntity entity) {
        Fill.rect(entity.getX(), entity.getY(), entity.drawSize(), entity.drawSize());
    }
}
