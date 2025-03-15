package bullethell.type;

import bullethell.core.Core;
import bullethell.entity.type.Player;
import bullethell.graphics.Draw;
import bullethell.utils.Interval;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    private int axis;
    private int movement = 0;

    public PlayerType(final String name) {
        this.name = name;

        speed = 6;
        focusSpeed = 3;

        TextureRegion idle = Core.atlas.findRegion(name + "-idle");
        TextureRegion left = Core.atlas.findRegion(name + "-left");
        TextureRegion right = Core.atlas.findRegion(name + "-right");

        if(idle != null) {
            for (TextureRegion tex : idle.split(32, 48)[0]) {
                baseSprites.add(tex);
            }
        } else fillArray(baseSprites);

        if(left != null) {
            for (TextureRegion tex : left.split(32, 48)[0]) {
                movementSprites.add(tex);
            }
        } else fillArray(movementSprites);

        if(right != null) {
            for (TextureRegion tex : right.split(32, 48)[0]) {
                movementSprites.add(tex);
            }
        } else fillArray(movementSprites);
    }
    void fillArray(Array<TextureRegion> tex) {
        tex.clear();
        for (int i = 0; i < movementSpritesAmount; i++) {
            tex.add(Draw.white);
        }
    }

    public void reset() {
    }

    public void changeAxisX(int axis) {
        // change movement index when changing axis
        movement = 0;
        this.axis = axis;
        if(axis != 0) v = 7;
        else v = 15;
    }

    float v = 5;
    public void draw(Player player) {
        if(interval.get(v)) {
            movement++;
        }

        if(axis == 0) {
            movement = movement % movementSpritesAmount;
        }
        else movement = Math.min(movement, movementSpritesAmount - 1);

        Draw.color();
        Draw.drawc(texByAxis(axis), player.getX(), player.getY(), player.drawSize(), player.drawSize());

        Draw.drawc(Core.atlas.findRegion("blue-small"), player.getX(), player.getY(), player.getSize(), player.getSize());

//        Draw.color();
//        Draw.fill(Core.atlas.findRegion("blue-small"), player.getX(), player.getY(), player.getSize(), player.getSize());
//        Draw.textMode();
//        Draw.text(Fonts.kelly16, movement + "", player.getX(), player.getY());
//        Draw.textEnd();
    }

    TextureRegion texByAxis(int axis) {
        return switch (axis) {
            case -1 -> movementSprites.get(movement);
            case 1 -> movementSprites.get(8 + movement);
            case 0 -> baseSprites.get(movement);
            default -> null;
        };
    }
}
