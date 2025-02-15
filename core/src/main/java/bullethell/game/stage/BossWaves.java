package bullethell.game.stage;

import bullethell.core.Events;
import bullethell.core.Vars;
import bullethell.game.Attack;
import bullethell.game.Ev;
import bullethell.game.spell.SpellCard;
import bullethell.type.BossType;
import com.badlogic.gdx.utils.Array;

public class BossWaves extends Attack {
    private Array<Attack> attacks;
    public int spellAmount = 0;

    public BossWaves(BossType type, Attack... waves) {
        this.attacks = new Array<>(waves);
        this.attacks.ordered = true;
//        Vars.game.summonBoss((e) -> {
//            e.set(200, 600);
//        }, type);

        Events.on(Ev.SpellCardEndEvent.class, ev -> {
            if (ev.card == current()) { // if current was ended spell
                nextEntry();
            }
        });

        for (Attack atk : attacks) {
            if(atk instanceof SpellCard) spellAmount++;
        }
    }

    public void nextEntry() {
        if(attacks.isEmpty()) return;
        attacks.removeIndex(0);
    }

    public Attack current() {
        if(attacks.isEmpty()) return null;

        return attacks.get(0);
    }

    // don't update time
    // update current attack instead
    // end when no attacks left
    @Override
    public void superUpdate() {
        if(current() == null) {
            kill();
            return;
        }
        // if next attack is spell: do summon thing and pause till spell end
        Attack current = current();
        if(current instanceof SpellCard spell) {
            if(Vars.game.bossSpell == spell) return;

            Vars.game.bossSpell(spell);
        }else {
            current.superUpdate();

            if(current.isEnd()) {
                current.end();
                nextEntry();
            }
        }
    }

    @Override
    public void draw() {
        if(current() == null) return;

        current().draw();
    }

    void kill() {
        end = true;
    }
    boolean end;

    @Override
    public boolean isEnd() {
        return end;
    }
}
