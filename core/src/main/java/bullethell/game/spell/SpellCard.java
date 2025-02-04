package bullethell.game.spell;

import bullethell.game.Attack;
import com.badlogic.gdx.utils.Align;

/*
Contains information about how stuff should behave
(spawn bullets, applying effects, spawning items etc.)
 */
public class SpellCard extends Attack {
    public static final int SC_NO_TIMEOUT = 0x1 << 1; // no timeout bonus
    public static final int SC_NO_SPELL_BURNOUT = 0x1 << 2; // no bonus will burn

    public final String name;
    public final int tags;

    public SpellCard(String name, final int tags) {
        this.name = name;
        this.tags = tags;
    }

    public SpellCard(String name) {
        this(name, 0);
    }
    // till time runs outs
}
