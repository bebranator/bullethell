package bullethell.game.stage6.nonspells;

import bullethell.core.Vars;
import bullethell.entity.type.BossEntity;
import bullethell.game.Attack;
import bullethell.type.BossType;

// show boss and hand control to boss controller
public class ShowBossWave extends Attack {
    // todo: dialogues
    public ShowBossWave(BossType type) {
        Vars.game.boss(e -> {
            e.type(type);
            e.setSize(type.hitboxSize);
        });
        lifetime = 10 * 60;
    }
}
