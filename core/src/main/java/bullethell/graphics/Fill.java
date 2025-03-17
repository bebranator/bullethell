package bullethell.graphics;

import bullethell.core.Core;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import static bullethell.core.Core.gl20;

// used for drawing shapes
public class Fill {
    public static ShapeRenderer shapes;
    public static Color tmp = new Color();
    public static void init() {
        shapes = new ShapeRenderer();
    }

    public static void begin() {
        shapes.setAutoShapeType(true);
        shapes.begin();
    }
    public static void alpha(float value) {
        tmp.set(shapes.getColor());

        tmp.a = value;
        shapes.setColor(tmp);
    }
    public static void alpha() {
        tmp.set(shapes.getColor());

        tmp.a = 1;
        shapes.setColor(tmp);
    }
    public static void transform(Matrix4 transform) {
        shapes.setTransformMatrix(transform);
    }
    public static void translate(float x, float y) {
        shapes.translate(x, y, 0);
    }
    public static void transform() {
        shapes.identity();
    }
    public static void scale(float x, float y) {
        shapes.scale(x, y, 0);
    }
    public static void end() {
        shapes.end();
    }
    public static void proj(Matrix4 matrix) {
        shapes.setProjectionMatrix(matrix);
    }
    public static void proj() {
        shapes.setProjectionMatrix(Core.camera.combined);
    }

    //     public void rect(float x, float y, float originX, float originY,
    //       float width, float height, float scaleX, float scaleY, float degrees, Color col1, Color col2, Color col3, Color col4) {
    public static void rect(float x, float y, float w, float h, float rotation) {
        shapes.rect(x, y, 0, 0, w, h, 1, 1, rotation);
    }
    public static void wideLine(float x1, float y1, float x2, float y2, float width) {
        shapes.rectLine(x1, y1, x2, y2, width);
    }

    public static void line(float x1, float y1, float x2, float y2) {
        shapes.line(x1, y1, x2, y2);
    }
    public static void line(float x1, float y1, Vector2 p2) {
        shapes.line(x1, y1, p2.x, p2.y);
    }

    public static void rect(float x, float y, float ox, float oy, float w, float h, float rotation) {
        shapes.rect(x, y, ox, oy, w, h, 1, 1, rotation);
    }

    public static void color(Color color) {
        shapes.setColor(color);
    }
    public static void flush() {
        shapes.flush();
    }
    public static void color() {
        shapes.setColor(Color.WHITE);
    }

    public static void circle(float x, float y, float radius) {
        shapes.circle(x, y, radius);
    }

    public static void circleTex(float x, float y, float radius) {
        Draw.drawc(Draw.circle, x - radius / 2, y - radius / 2, radius * 2, radius * 2);
    }
    public static void set(ShapeRenderer.ShapeType type) {
        shapes.set(type);
    }
    public static void filled() {
        shapes.set(ShapeRenderer.ShapeType.Filled);
    }
    public static void line() {
        shapes.set(ShapeRenderer.ShapeType.Line);
    }
    public static void rect(float x, float y, float w, float h) {
        shapes.rect(x, y, w, h);
    }
}
