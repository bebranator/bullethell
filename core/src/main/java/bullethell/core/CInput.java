package bullethell.core;

import bullethell.utils.Tmp;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntSet;

// just anuken's arc input code
public class CInput extends InputAdapter {
    public static Input gdx = Gdx.input;
    static final Vector2 mouse = new Vector2(), mouseWorld = new Vector2();

    private final IntSet pressed = new IntSet(), lastFramePress = new IntSet(), justPressed = new IntSet();

    public void process() {
        lastFramePress.clear();
        lastFramePress.addAll(pressed);
        justPressed.clear();
    }

    public boolean isJustPressed(int key) {
        return justPressed.contains(key);
    }

    public boolean isPressed(int key) {
        if(key < 0) return pressed.notEmpty();

        return pressed.contains(key);
    }

    public boolean isReleased(int key){
        return !isPressed(key) && lastFramePress.contains(key);
    }

    @Override
    public boolean keyUp(int keycode) {
        pressed.remove(keycode);
        return false;
    }


    @Override
    public boolean keyDown(int keycode) {
        pressed.add(keycode);
        justPressed.add(keycode);
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
