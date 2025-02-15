package bullethell.type;

import bullethell.core.Core;
import bullethell.entity.type.Player;
import bullethell.graphics.Draw;
import bullethell.module.Fonts;
import bullethell.module.Tex;
import bullethell.utils.Interval;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class PlayerType {
    public static final Interval interval = new Interval(1);
    public final String name;

    public Array<TextureRegion> baseSprites = new Array<>();
    // 0-7 = left; 8-15 = right
    public Array<TextureRegion> movementSprites = new Array<>();

    public int movementSpritesAmount = 8; // for each direction (left and right)
    public float speed, focusSpeed;
    public float size, focusSize; // hit box sizes

    private int movement = 0;

    public PlayerType(final String name) {
        this.name = name;

        speed = 6;
        focusSpeed = 3;

        TextureRegion idle = Core.atlas.findRegion(name + "-idle");
        if(idle == null) {
            for (int i = 0; i < movementSpritesAmount; i++) {

            }
        }
    }
    void fillArray(Array<TextureRegion> tex) {
        tex.clear();
        for (int i = 0; i < movementSpritesAmount; i++) {
            tex.add(Draw.white);
        }
    }

    public void reset() {
        movement = 0;
    }

    public void changeAxisX(int axis) {
        if(axis == 0) movement = 16;
        else if (axis == -1) movement = 8;
        else movement = 24;
    }

    int a = 0;
    public void draw(Player player) {
        a = player.axisX + 1;

        // just trying optimization later
        // -1
        if(a == 0) {
            movement = Math.max(movement - 1, 0);
        } else if (a == 1) { // 0
            movement = Math.max(movement - 1, 8);
        } else if (a == 2) { // 1
            movement = Math.max(movement - 1, 8);
        }
        Draw.color();
        Draw.fill(Core.atlas.findRegion("blue-small"), player.getX(), player.getY(), player.getSize(), player.getSize());
        Draw.textMode();
        Draw.text(Fonts.kelly16, movement + "", player.getX(), player.getY());
        Draw.textEnd();
    }
}
