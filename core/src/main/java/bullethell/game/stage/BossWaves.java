package bullethell.game.stage;

import bullethell.game.Attack;
import bullethell.struct.CArray;

// todo: fix double end() call error
public class BossWaves extends Attack {
    private CArray<Attack> attacks;
    private CArray<Attack> timedOut;
    private int index = 0;
    private Attack previous = null, current = null;

    public BossWaves(Attack... attacks) {
        this.attacks = new CArray<>(attacks);
        this.timedOut = new CArray<>();
    }

    public void setAttackIndex(int index) {
        previous = current;
        current = attacks.getOrNull(index);

        if(previous != null) {
            timedOut.add(previous);
            previous.end();
        }
        // no more attacks
        if(current == null) return;
        current.begin();
    }

    @Override
    public void reset() {
        previous = null;
        current = attacks.getOrNull(0);
        for(Attack atk : timedOut) atk.reset();
        timedOut.clear();
    }

    @Override
    public void begin() {
        setAttackIndex(0);
    }

    @Override
    protected void update() {
        // todo: cast spell
        if(current == null) return;

        current.superUpdate();

        if(current.isEnd()) {
            setAttackIndex(++index);
        }
    }

    @Override
    public boolean isEnd() {
        return current == null;
    }

    @Override
    public String debug() {
        return current == null ? "" : current.debug();
    }
}
