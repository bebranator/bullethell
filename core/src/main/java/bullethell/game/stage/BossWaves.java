package bullethell.game.stage;

import bullethell.core.Events;
import bullethell.core.Vars;
import bullethell.game.Attack;
import bullethell.game.Ev;
import bullethell.game.spell.SpellCard;
import bullethell.log.Log;
import bullethell.type.BossType;
import com.badlogic.gdx.utils.Array;

public class BossWaves extends Attack {
    private Array<Attack> attacks;
    private Attack previous = null, current = null;
    public int spellAmount = 0;
    public int index = 0;

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
    }
    public void setAttack(int index) {
        previous = current;
        current = getEntry(index);

        if(previous != current && current != null) {

            current.begin();
        }

        if(previous != current && previous != null) {
            Log.info("PREVIOUS END");
            previous.end();
        }
    }

    public Attack getEntry(int index) {
        if(attacks.isEmpty()) return null;
        if(index >= attacks.size) return null;
        return attacks.get(index);
    }

    public void nextEntry() {
        Log.info("ENTRY: " + ++index);
        setAttack(index);
    }

    public Attack current() {
        return current;
    }

    // don't update time
    // update current attack instead
    // end when no attacks left
    @Override
    public void superUpdate() {
        current = current();

        if(current == null) {
            kill();
            return;
        }

        // if next attack is spell: do summon thing and pause till spell end
        if(current instanceof SpellCard spell) {
            if(Vars.game.bossSpell == spell) return;

            Vars.game.bossSpell(spell);
        }else {
            current.superUpdate();

            if(current.isEnd()) {
                nextEntry();
            }
        }
    }

    @Override
    public void draw() {
        if(current == null) return;

        current.draw();
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
