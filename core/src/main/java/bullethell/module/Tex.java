package bullethell.module;

import bullethell.assets.Assets;
import bullethell.utils.C;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

// error moved to Assets
public class Tex {
    public static Drawable black6;

    public static void init() {
        black6 = new TextureRegionDrawable(Assets.white).tint(C.black6);
    }
}
