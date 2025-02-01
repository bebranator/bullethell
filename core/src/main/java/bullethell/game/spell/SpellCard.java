package bullethell.game.spell;

import bullethell.game.Attack;

/*
Contains information about how stuff should behave
(spawn bullets, applying effects, spawning items etc.)
 */
public class SpellCard extends Attack {
    @Override
    public boolean isEnd() {
        return false;
    }
}
