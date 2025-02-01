package bullethell.graphics;

import bullethell.core.Core;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Fill {
    public static ShapeRenderer shapes;
    public static void init() {
        shapes = new ShapeRenderer();
    }

    public static void begin() {
        shapes.begin();
        shapes.setAutoShapeType(true);
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

    public static void rect(float x, float y, float ox, float oy, float w, float h, float rotation) {
        shapes.rect(x, y, ox, oy, w, h, 1, 1, rotation);
    }

    public static void color(Color color) {
        shapes.setColor(color);
    }
    public static void color() {
        shapes.setColor(Color.WHITE);
    }

    public static void circle(float x, float y, float radius) {
        shapes.circle(x, y, radius);
    }

    public static void circleTex(float x, float y, float radius) {
        Draw.fill(Draw.circle, x - radius / 2, y - radius / 2, radius * 2, radius * 2);
    }
    /*
      public static void fillEllipse(Batch batch, float x, float y, float width, float height) {
        drawScaled(batch, circle50, x - width / 2.0F, y - height / 2.0F, width / 50.0F, height / 50.0F);
      }
     */
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
