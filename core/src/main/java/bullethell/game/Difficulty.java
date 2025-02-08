package bullethell.game;

public enum Difficulty {
    easy(.8f), normal(1f),
    hard(1.2f), expert(1.4f),
    lunatic(1.5f); // lunatic is other extra

    public final float bonusModifier;
    Difficulty(float bonusModifier) {
        this.bonusModifier = bonusModifier;
    }
}
