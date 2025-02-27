package bullethell.game.stage6.spells;

import bullethell.content.Bullets;
import bullethell.core.Core;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.EnemyEntity;
import bullethell.game.GameTime;
import bullethell.game.spell.SpellCard;
import bullethell.log.Log;
import bullethell.type.EnemyType;
import bullethell.utils.Interval;

// survival spell
public class DanmakuLabyrinth extends SpellCard {
    private final Interval interval = new Interval(1);
    private int iterations = 0;

    private EnemyEntity summoner;

    public DanmakuLabyrinth() {
        super("danmaku_labyrinth", 5000000, SC_NO_BONUS_BURN);
        lifetime = 5 * 60;
    }

    @Override
    public void begin() {
        summoner = EnemyEntity.spawn(new EnemyType("ghost-entity") {{
            health = 1;
            size = 48;
            drawSize = 48;
        }}, (ent) -> {
            ent.damageScale = 0;
            ent.set(width() / 2 + ax(), height() - height() / 5 + ay());
        });

        summoner.updater = (e) -> {
            e.params().linear(-cosd(time) * 4, 0);
        };
    }

    int amount = byDifficulty(1, 2, 3, 4, 6);
    int itcooldown = byDifficulty(6, 4, 4, 4, 3);
    float reload = byDifficulty(1.5f, 1.2f, 1, .7f, .7f) * 60;

    @Override
    protected void update() {
        if(time < 40) return;

        if(interval.get(reload)) summonBullets();
    }

    @Override
    public void end() {
        Log.info("CALL");
        super.end();
        summoner.remove();
        summoner = null;
    }

    void summonBullets() {
        iterations++;

        if(iterations % itcooldown == 0) {
//            aimedBullet((b) -> {
//                b.drawSize(16);
//                b.setSize(4);
//            }, Bullets.blueSmall, summoner.getX(), summoner.getY(), 2, .05f);
//            Bullet.spawn((e) -> {
//                e.setSize(8);
//                e.drawSize(12);
//                e.params().towards(1, 0, player().getX(), player().getY(), .005f, 0f);
//            }, Bullets.blueSmall, summoner.getX(), summoner.getY());
            aimedLinearBullet((e) -> {
                e.setSize(8);
                e.drawSize(12);
            }, Bullets.blueSmall, summoner.getX(), summoner.getY(), 2);
        }
    }

    @Override
    public String debug() {
        return "summoner: " + summoner + "\npos: " + summoner.position() + "\nbirth time: " + summoner.birthTime();
    }
}
