package bullethell.game.spell;

import bullethell.core.Vars;
import bullethell.entity.type.BossEntity;
import bullethell.entity.type.Player;
import bullethell.game.Attack;
import com.badlogic.gdx.utils.Align;

/*
Contains information about how stuff should behave
(spawn bullets, applying effects, spawning items etc.)
 */
public class SpellCard extends Attack {
    public static final int SC_NO_TIMEOUT_BONUS = 0x1 << 1; // no bonus on timeout
    public static final int SC_NO_BONUS_BURN = 0x1 << 2; // no bonus will burn
    public static final int SC_NO_POWER_DROPS = 0x1 << 3; // boss will not drop any power while in spell state

    public final String name;
    public final int tags;
    public final int spellBonus;

    public SpellCard(String name, int spellBonus, final int tags) {
        this.name = name;
        this.tags = tags;
        this.spellBonus = spellBonus;
    }

    public SpellCard(String name) {
        this(name, 5000000, 0);
    }

    public BossEntity boss() {
        return Vars.game.bossEntity;
    }
    // if bigger than 0 then its has flags
    public boolean hasTags(int mask) {
        return (this.tags & mask) != 0;
    }
    // till time runs outs
}
