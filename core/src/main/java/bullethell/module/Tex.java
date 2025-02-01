package bullethell.module;

import bullethell.core.Core;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tex {
    public static Drawable black6, white;

    public static void init() {
        TextureRegionDrawable white = new TextureRegionDrawable(Core.atlas.findRegion("pixel"));
        Tex.white = white;

        black6 = tint(white, 0, 0, 0, 0.6f);
    }

    static Drawable tint(TextureRegionDrawable src, float r, float g, float b, float a) {
        return src.tint(new Color(r, g, b, a));
    }
}
