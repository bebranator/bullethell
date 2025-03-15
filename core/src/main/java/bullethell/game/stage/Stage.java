package bullethell.game.stage;

import bullethell.core.Vars;
import bullethell.game.Attack;

import bullethell.struct.CArray;
import bullethell.type.BossType;

/*
Stage() {
    waves(
        new Stage1GhostsRush0(),
        new Stage1GhostsRush1(),
        boss(
            BossTypes.ghostGirl,
            new NonSpell1(),
            difficultyHigher(Difficulty.easy, new GhostSpell0()).orElse(new spell1()),
            new NonSpell2()
        )
    );
}
 */
public class Stage {
    // bro why
    // fucking big russian C
    protected CArray<Attack> waves;
    protected int index = 0;
    protected Attack previous = null, current = null;

    public Stage() {
        waves = new CArray<>();
    }

    public void setAttackIndex(int index) {
        previous = current;
        current = waves.getOrNull(index);

        if(previous != null) previous.end();
        // no more attacks
        if(current != null) current.begin();
    }

    public void begin() {
//        setAttackIndex(0);
        resetAttackIndex();
        current.begin();
    }

    public void end() {
        current.end();
    }

    protected void boss(BossType type, Attack... attacks) {
//        summonBoss(type);
        add(new BossWaves(attacks));
    }

    public void reset() {
        for(Attack atk : waves) {
            atk.time = 0;
            atk.reset();
        }
        resetAttackIndex();
    }
    protected void resetAttackIndex() {
        previous = null;
        index = 0;
        current = waves.getOrNull(index);
    }

    protected void waves(Attack... ar) {
        this.waves.addAll(ar);
    }
    protected void add(Attack attack) {
        waves.add(attack);
    }

    public void update() {
        if(current == null) {
            endStage();
            return;
        }

        current.superUpdate();

        if(current.isEnd()) {
            setAttackIndex(++index);
        }
    }
    public void endStage() {
        Vars.ui.stageResultsDialog.show();
    }

    // draw stage background
    public void draw() {
        if(current == null) return;
        current.draw();
    }

    // end current attack
    public void endAttack() {
        setAttackIndex(++index);
    }

    public Attack current() {
        return current;
    }
    public String debug() {
        return "current attack: " + current() + "\nindex: " + index;
    }
}
