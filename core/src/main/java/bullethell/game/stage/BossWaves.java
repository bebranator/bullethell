package bullethell.game.stage;

import bullethell.game.Attack;
import bullethell.struct.CArray;

// todo: fix double end() call error
public class BossWaves extends Attack {
    private CArray<Attack> attacks;
    private CArray<Attack> timedOut;
    private int index = 0;
    private Attack previous = null, current = null, next;

    public BossWaves(Attack... attacks) {
        this.attacks = new CArray<>(attacks);
        this.timedOut = new CArray<>();
    }

    @Override
    public void reset() {
        previous = null;
        current = attacks.getOrNull(0);
        for(Attack atk : timedOut) atk.reset();
        timedOut.clear();
        index = 0;
    }

    @Override
    public void begin() {
        current = attacks.getOrNull(0);
        current.begin();
    }

    @Override
    protected void update() {
        if(current == null) return;

        current.superUpdate();

        if(current.isEnd()) {
            current.end();

            previous = current;
            current = attacks.getOrNull(++index);

            if(current != null) current.begin();
        }
    }

    @Override
    public boolean isEnd() {
        return current == null;
    }

    @Override
    public String debug() {
        return "BOSSWAVES:" + "\nindex: " + index + "\nnext_coming" + (next = attacks.getOrNull(index + 1))
            + "\ncurrent attack debug:\n" + (current == null ? "" : current.debug());
    }
}
