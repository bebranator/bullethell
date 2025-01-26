package bullethell.game.dialog;

import bullethell.func.Cons;
import com.badlogic.gdx.utils.Array;

/*
example of dialog:

static GameDialog dial_stage_4 = GameDialog.begin(builder -> {
    builder.time(.5f); // 0.5 seconds to fully end line
    builder.hero(raymoo);
    builder.text("Òû êòî????");
    builder.finished().text("äà");

    builder.time(.5f);
    builder.hero(Chars.bazarov);
    builder.text("ÒÛ ÊÒÎ???");

    builder.time();
    builder.hero(raymoo);
    builder.text("õç ïîí");
    // signals that dialog is finished, and we need to panic
    builder.finished().consumed();
});
 */
public class GameDialog {
    private Array<GameDialogEntry> entries;
    public GameDialog(Array<GameDialogEntry> entries) {
        this.entries = entries;
    }

    public static GameDialog begin(Cons<GameDialogBuilder> builderCons) {
        return new GameDialogBuilder().get(builderCons).build();
    }

    // todo: string interpolator (show only part)
    public void update() {

    }
}
