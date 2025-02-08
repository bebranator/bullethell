package bullethell.game.spell;

public class SpellCardEndInfo {
    private static final SpellCardEndInfo instance = new SpellCardEndInfo();

    public boolean died, spellTimeout;
    public int spellBonus;
    public float spellTimeLeft;
    public SpellCard spell;

    public static SpellCardEndInfo get() {
        instance.died = false;
        instance.spellTimeout = false;
        instance.spell = null;
        instance.spellBonus = 0;
        instance.spellTimeLeft = 0;

        return instance;
    }
}
