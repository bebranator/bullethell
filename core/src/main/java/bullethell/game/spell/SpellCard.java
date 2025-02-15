package bullethell.game.spell;

import bullethell.core.Vars;
import bullethell.entity.type.BossEntity;
import bullethell.game.Attack;
import com.badlogic.gdx.math.MathUtils;

import static com.badlogic.gdx.math.MathUtils.*;

/*
Contains information about how stuff should behave
(spawn bullets, applying effects, spawning items etc.)
 */
public class SpellCard extends Attack {
    public static final int SC_NO_TIMEOUT_BONUS = 1 << 1; // no bonus on timeout
    public static final int SC_NO_BONUS_BURN = 1 << 2; // no bonus will burn
    public static final int SC_NO_POWER_DROPS = 1 << 3; // boss will not drop any power while in spell state
    public static final int SC_NO_DEATH_BONUS_LOSS = 1 << 4;

    public final String name;
    public final int tags;
    public final int spellBonus;

    public SpellCard(String name, int spellBonus, final int tags) {
        this.name = name;
        this.tags = tags;
        this.spellBonus = spellBonus;
    }

    public SpellCard(String name) {
        this(name, 5000000, 0);
    }

    protected BossEntity boss() {
        return Vars.game.bossEntity;
    }
    protected float clamp(float value, float min, float max) {
        return MathUtils.clamp(value, min, max);
    }
    protected int clamp(int val, int m, int mx) {
        return MathUtils.clamp(val, m, mx);
    }
    protected float sind(float deg) {
        return sinDeg(deg);
    }
    protected float cosd(float deg) {
        return cosDeg(deg);
    }
    protected float tand(float deg) {
        return tanDeg(deg);
    }
    protected float max(float a, float b) {
        return Math.max(a, b);
    }
    protected float min(float a, float b) {
        return Math.min(a, b);
    }
    protected float pi() {
        return PI;
    }
    protected float pi2() {
        return PI2;
    }
    // if bigger than 0 then its has flags
    public boolean hasTags(int mask) {
        return (this.tags & mask) != 0;
    }
    // till time runs outs
}
