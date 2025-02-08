package bullethell.game.stage;

import bullethell.core.Events;
import bullethell.core.Vars;
import bullethell.game.Attack;
import bullethell.game.Ev;
import bullethell.game.spell.SpellCard;
import bullethell.type.BossType;
import com.badlogic.gdx.utils.Array;

public class BossWaves extends Attack {
    private Array<Attack> waves;
    public BossWaves(BossType type, Attack... waves) {
        this.waves = new Array<>(waves);
        this.waves.ordered = true;
//        Vars.game.summonBoss((e) -> {
//            e.set(200, 600);
//        }, type);

        Events.on(Ev.SpellCardEndEvent.class, ev -> {
            if(ev.card == current()) { // if current was ended spell
                nextEntry();
            }
        });
    }

    public void nextEntry() {
        if(waves.isEmpty()) return;
        waves.removeIndex(0);
    }

    public Attack current() {
        if(waves.isEmpty()) return null;

        return waves.get(0);
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
