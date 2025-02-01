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

    protected void waves(Attack... ar) {
        this.waves.addAll(ar);
    }

    public void update() {
        // todo: do things with blank stages
        if(waves.isEmpty() || index >= waves.size) return;

        Attack current = waves.get(index);
        current.superUpdate();

        if(current.isEnd()) {
            current.end();
            index++;
        }
    }

    // draw stage background
    public void draw() {

    }
}
