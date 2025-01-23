package bullethell.ui.dialog;

import bullethell.module.Bindings;
import bullethell.module.Styles;
import bullethell.utils.KeyListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class PauseDialog extends CDialog {
    // todo: options and some conditions
    private int index = 0;

    public PauseDialog() {
        super("PAUSE", Styles.defWindow);

        shouldPause = true;
        addListener(new KeyListener(this::inputDown, this::inputUp));
        shown(this::rebuild);
    }

    void rebuild() {
        container.clearChildren();
    }

    void accept() {

    }
    void down() {

    }
    void up() {

    }

    // put down key
    boolean inputDown(InputEvent event, int key) {
        if(key == Bindings.down) down();
        if(key == Bindings.up) up();
        if(key == Bindings.accept) accept();

        return false;
    }

    boolean inputUp(InputEvent event, int key) {
        return false;
    }
}
