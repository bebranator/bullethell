package bullethell.core;

import bullethell.utils.Tmp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

// todo: better input wrapper
public class CInput {
    public static Input gdx = Gdx.input;
    static final Vector2 mouse = new Vector2(), mouseWorld = new Vector2();

    public boolean isJustPressed() {
        return false;
    }

    public Vector2 mouseWorld() {
        // bad but why libgdx need 3d coordinates
        Tmp.v31.set(Core.camera.unproject(Tmp.v32.set(mouse.x, mouse.y, 0f)));

        return Tmp.v21.set(Tmp.v31.x, Tmp.v32.y);
    }

    public Vector2 mouse() {
        return mouse.set(gdx.getX(), gdx.getY());
    }
}
