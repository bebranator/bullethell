package bullethell.module;

import bullethell.assets.Assets;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tex {
    static TextureRegion error;
    static Texture errorTexture;

    public static void init() {
        errorTexture = Assets.get("sprites/error.png", Texture.class);
        error = new TextureRegion(errorTexture);
    }

    public static TextureRegion error() {
        return error;
    }
}
