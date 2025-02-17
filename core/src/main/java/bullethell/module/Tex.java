package bullethell.module;

import bullethell.core.Core;
import bullethell.graphics.Draw;
import bullethell.utils.Textures;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tex {
    public static Drawable black6, white;

    public static Texture twhite, tblack;

    public static void init() {
        TextureRegionDrawable white = new TextureRegionDrawable(Core.atlas.findRegion("pixel"));
        Tex.white = white;

        black6 = tint(white, 0, 0, 0, 0.6f);
        twhite = Textures.cut(Draw.white);

        Pixmap tblack6pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        tblack6pixmap.setColor(Color.BLACK);
        tblack6pixmap.drawPixel(0, 0);
        tblack = new Texture(tblack6pixmap);
    }

    static Drawable tint(TextureRegionDrawable src, float r, float g, float b, float a) {
        return src.tint(new Color(r, g, b, a));
    }
}
