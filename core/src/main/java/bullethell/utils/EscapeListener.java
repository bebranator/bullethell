package bullethell.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class EscapeListener extends InputListener {
    private final Runnable r;

    public EscapeListener(Runnable run) {
        this.r = run;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if (keycode == Input.Keys.ESCAPE) r.run();

        return false;
    }
}
