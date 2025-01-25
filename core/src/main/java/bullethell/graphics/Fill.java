package bullethell.graphics;

import bullethell.core.Core;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Fill {
    public static final ShapeDrawer shaper = new ShapeDrawer(Core.batch);
    static {
        shaper.setTextureRegion(Draw.white);
    }

    public static void circle(float x, float y, float radius) {
        TextureRegion circle = Draw.circle;
        float c = radius * .5f;

        // because circle"50"
        // x-c to center
        Draw.draw(circle, x - c, y - c, radius * 2, radius * 2);
    }
    /*
      public static void fillEllipse(Batch batch, float x, float y, float width, float height) {
        drawScaled(batch, circle50, x - width / 2.0F, y - height / 2.0F, width / 50.0F, height / 50.0F);
      }
     */
    public static void rect(float x, float y, float w, float h) {
        shaper.rectangle(x, y, w, h);
    }
}
