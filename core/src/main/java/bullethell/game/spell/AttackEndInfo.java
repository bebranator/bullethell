package bullethell.game.spell;

import bullethell.game.Attack;

public class AttackEndInfo {
    private static final AttackEndInfo instance = new AttackEndInfo();

    public boolean died, timeout;
    public float timeLeft;
    public Attack attack;

    public static AttackEndInfo get() {
        instance.died = false;
        instance.timeout = false;
        instance.attack = null;
        instance.timeLeft = 0;

        return instance;
    }
}
