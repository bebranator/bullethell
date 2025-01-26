package bullethell.game.dialog;

import bullethell.content.Chars;
import bullethell.func.Cons;
import bullethell.type.Hero;
import com.badlogic.gdx.utils.Array;

public class GameDialogBuilder {
    // actually, order sensitive
    private Array<GameDialogEntry> dialogEntries = new Array<>();
    private GameDialogEntry temp = new GameDialogEntry();

    public GameDialogBuilder get(Cons<GameDialogBuilder> builder) {
        builder.get(this);
        return this;
    }
    public void dispose() {
        dialogEntries = null;
        temp = null;
    }
    // should be defined right before dialog text
    // because it will trigger creating new dialog object

    public void time(float time) {
        GameDialogEntry entry = new GameDialogEntry();
        entry.finished = temp.finished;
        entry.hero = temp.hero;
        entry.text = temp.text;
        entry.time = time;
        dialogEntries.add(entry);

        temp.time = 1f;
        temp.hero = Chars.bazarov;
        temp.text = "";
        temp.finished = () -> {};
    }
    // sets current hero

    public void hero(Hero hero) {
        temp.hero = hero;
    }
    public void text(String text) {
        temp.text = text;
    }
    public void finished(Runnable finish) {
        temp.finished = finish;
    }
    public GameDialog build() {
        return new GameDialog(dialogEntries);
    }
}
