package bullethell.game;

public class SpellState {
    public int bonus;
    public boolean died;

    public void reset() {
        bonus = 0;
        died = false;
    }
}
