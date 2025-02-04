package bullethell.game.stage;

import bullethell.game.Attack;
import com.badlogic.gdx.utils.Array;

public class Stage {
    protected Array<Attack> waves;
    protected int index;

    public Stage() {
        waves = new Array<>();
        index = 0;
    }
    public void reset() {
        index = 0;
        for(Attack atk : waves) {
            atk.time = 0;
        }
    }

    // returns null if no attacks
    public Attack current() {
        if(index + 1 > waves.size) return null;

        return waves.get(index);
    }

    protected void waves(Attack... ar) {
        this.waves.addAll(ar);
    }

    public void update() {
        // todo: do things with blank stages
//        if(waves.isEmpty() || index >= waves.size) return;

        Attack current = current();

        if(current == null) return;
        current.superUpdate();

        if(current.isEnd()) {
            current.end();
            index++;
        }
    }

    // draw stage background
    public void draw() {
        Attack current = current();

        if(current == null) return;
        current.draw();
    }
}
