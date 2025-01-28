package bullethell.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

// todo: better input wrapper
public class CInput {
    public static Input gdx = Gdx.input;

    public boolean isJustPressed() {
        return false;
    }
}
