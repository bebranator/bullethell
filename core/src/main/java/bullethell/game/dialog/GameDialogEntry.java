package bullethell.game.dialog;

import bullethell.type.Hero;

public class GameDialogEntry {
    public String text;
    public Hero hero;
    public float time;
    public Runnable finished;
}
