package bullethell.module;

import bullethell.core.Core;
import com.badlogic.gdx.Input;

import static com.badlogic.gdx.Input.Keys.*;

public class Bindings {
    public static int up = UP, down = DOWN, accept = ENTER;

    public static int moveUp = W, moveDown = S, moveLeft = A, moveRight = D;

    public static int axis(int k1, int k2) {
        boolean a = Core.input.isKeyPressed(k1);
        boolean b = Core.input.isKeyPressed(k2);

        /*
                    return keyboard.isPressed(axis.min) && keyboard.isPressed(axis.max) ? 0 :
                    keyboard.isPressed(axis.min) ? -1 : keyboard.isPressed(axis.max) ? 1 : 0;
         */
        // if (a and b) are false: 0, if a: 1, if b: -1

        // tf
        return (a && b) ? 0 : a ? -1 : b ? 1 : 0;
    }
}
