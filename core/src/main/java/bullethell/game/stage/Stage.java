package bullethell.game.stage;

import bullethell.core.Vars;
import bullethell.game.Attack;
import com.badlogic.gdx.utils.Array;

public class Stage {
    protected Array<Attack> waves;
    protected int index;
    protected Attack previous, current;

    public Stage() {
        waves = new Array<>();
        index = 0;
    }

    public void begin() {
        index = -1;
        nextAttack();
    }
    public void end() {
        reset();
    }

    public void reset() {
        index = 0;
        for(Attack atk : waves) {
            atk.time = 0;
            atk.reset();
            atk.end();
        }
    }

    // returns null if no attacks
    public Attack current() {
        if(index + 1 > waves.size) return null;

        return current = waves.get(index);
    }

    protected void waves(Attack... ar) {
        this.waves.addAll(ar);
    }

    public void update() {
        // todo: do things with blank stages
//        if(waves.isEmpty() || index >= waves.size) return;

        Attack current = current();

        if(current == null) {
            onEnd();
            return;
        }

        current.superUpdate();

        if(current.isEnd()) {
            current.end();
            nextAttack();
        }
    }
    public void onEnd() {
        Vars.ui.stageResultsDialog.show();
    }

    public void nextAttack() {
        previous = current;
        index++;
        current = current();

        if(previous != null && previous != current) previous.end();

        if(current != null && previous != current) current.begin();
    }

    // draw stage background
    public void draw() {
        Attack current = current();

        if(current == null) return;
        current.draw();
    }
}
