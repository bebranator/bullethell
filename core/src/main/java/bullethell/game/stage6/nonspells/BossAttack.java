package bullethell.game.stage6.nonspells;

import bullethell.core.Vars;
import bullethell.entity.type.BossEntity;
import bullethell.game.Attack;

public class BossAttack extends Attack {
    public BossEntity boss() {
        return Vars.game.bossEntity;
    }
}
