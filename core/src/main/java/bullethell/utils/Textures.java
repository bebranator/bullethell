package bullethell.utils;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {
    /*
    returns texture region cut from junk
     */
    public static Texture cut(TextureRegion source) {
        Texture tex = source.getTexture();

        Pixmap map = new Pixmap(source.getRegionWidth(), source.getRegionHeight(), tex.getTextureData().getFormat());

        int srcX = source.getRegionX();
        int srcY = source.getRegionY();
        int srcWidth = source.getRegionWidth();
        int srcHeight = source.getRegionHeight();

        tex.getTextureData().prepare();
//Pixmap pixmap, int srcx, int srcy, int srcWidth, int srcHeight, int dstx, int dsty, int dstWidth, int dstHeight
        map.drawPixmap(tex.getTextureData().consumePixmap(), srcX, srcY, srcWidth, srcHeight, 0, 0,
            source.getRegionWidth(), source.getRegionHeight());
        return new Texture(map);
    }
}
