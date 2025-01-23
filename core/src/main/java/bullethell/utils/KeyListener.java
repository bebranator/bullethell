package bullethell.utils;

import bullethell.func.Pred0;
import bullethell.func.Pred2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class KeyListener extends InputListener {
    public Pred2<InputEvent, Integer> down, up;

    public KeyListener(final Pred2<InputEvent, Integer> down, final Pred2<InputEvent, Integer> up) {
        this.down = down;
        this.up = up;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        return down.get(event, keycode);
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        return up.get(event, keycode);
    }
}
