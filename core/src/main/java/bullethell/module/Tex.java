package bullethell.module;

import bullethell.core.Core;
import bullethell.graphics.Draw;
import bullethell.utils.Textures;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tex {
    public static Drawable black6, white;

    public static TextureRegion whiteRegion;

    public static Texture twhite, tblack;

    public static void init() {
        TextureRegionDrawable white = new TextureRegionDrawable(Core.atlas.findRegion("pixel"));
        Tex.white = white;

        whiteRegion = white.getRegion();

        black6 = tint(white, 0, 0, 0, 0.6f);
//        twhite = Textures.cut(Draw.white);
        Pixmap tWhitePixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        tWhitePixmap.setColor(Color.WHITE);
        tWhitePixmap.drawPixel(0, 0);

        twhite = new Texture(tWhitePixmap);

        Pixmap tBlackPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        tBlackPixmap.setColor(Color.BLACK);
        tBlackPixmap.drawPixel(0, 0);

        tblack = new Texture(tBlackPixmap);
    }

    static Drawable tint(TextureRegionDrawable src, float r, float g, float b, float a) {
        return src.tint(new Color(r, g, b, a));
    }

    public static Texture cut(TextureRegion region) {
        TextureRegionDrawable draw;
        Pixmap map = new Pixmap(region.getRegionWidth(), region.getRegionHeight(),
            region.getTexture().getTextureData().getFormat());
        region.getTexture().draw(map, 0, 0);

        return new Texture(map);
    }
}
