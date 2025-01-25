package bullethell.game.dialog;

import bullethell.func.Cons;

public class GameDialogBuilder {
    public GameDialogBuilder() {

    }

    public GameDialogBuilder get(Cons<GameDialogBuilder> builder) {
        return this;
    }

    public GameDialog build() {
        return new GameDialog();
    }
}
