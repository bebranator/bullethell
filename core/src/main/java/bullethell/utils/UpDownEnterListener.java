package bullethell.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class UpDownEnterListener extends InputListener {
    private Runnable up, down, enter;

    public UpDownEnterListener(Runnable up, Runnable down, Runnable enter) {
        this.up = up;
        this.down = down;
        this.enter = enter;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                up.run();
                break;
            case Input.Keys.DOWN:
                down.run();
                break;
            case Input.Keys.ENTER:
                enter.run();
                break;
            default:
                break;
        }

        return false;
    }
}
