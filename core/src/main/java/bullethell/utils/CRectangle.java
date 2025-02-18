package bullethell.utils;

import com.badlogic.gdx.math.*;

// rectangle with ability to rotate
// used for collision stuff
public class CRectangle {
    // in degress
    public float rotation, x, y, w, h;
    // offsets by x and y
    // center by default
    public float originX = 0, originY = 0;

    public CRectangle() {
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
    public void rotate(float rotation) {
        this.rotation += rotation;
    }

    public void transform(Vector2 pos, float rotation) {
        float cos = MathUtils.cosDeg(rotation);
        float sin = MathUtils.sinDeg(rotation);

        float ox = x + originY;
        float oy = y + originY;

        float x = pos.x - ox;
        float y = pos.y - oy;
        pos.set(
            (cos * x + sin * y) + ox,
            (cos * y + sin * x) + oy
        );
    }
    public void transform(Circle pos, float rotation) {
        float cos = MathUtils.cosDeg(rotation);
        float sin = MathUtils.sinDeg(rotation);

        // origin coordinates
        float ox = x + originX;
        float oy = y + originY;

        float x = pos.x - ox;
        float y = pos.y - oy;

        pos.setPosition(
            (cos * x + sin * y) + ox,
            (cos * y + sin * x) + oy
        );
    }
    public boolean circleCollision(Circle circle) {
        Tmp.c1.set(circle);

        Circle c = Tmp.c1;

        transform(c, rotation);
        return Intersector.overlaps(c, Tmp.r1.set(x, y, w, h));
    }
}
