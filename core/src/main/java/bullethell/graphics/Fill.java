package bullethell.graphics;

import bullethell.core.Core;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
