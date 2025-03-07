package bullethell.game.dialog;

import bullethell.func.Cons;
import com.badlogic.gdx.utils.Array;

/*
Game dialog should behave like this:

1. level stops updating but entities remain updated
2. signals that current attack ended.
3. restrict player shot
 */
public class GameDialog {
    private Array<GameDialogEntry> entries;
    public GameDialog(Array<GameDialogEntry> entries) {
        this.entries = entries;
    }

    public static GameDialog begin(Cons<GameDialogBuilder> builderCons) {
        return new GameDialogBuilder().get(builderCons).build();
    }

    public void update() {

    }
}
