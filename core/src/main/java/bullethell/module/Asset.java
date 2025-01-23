package bullethell.module;

import bullethell.core.Core;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Asset {
    private TextureAtlas atlas;

    public Asset() {
        atlas = new TextureAtlas(Core.files.internal("sprites.atlas"));
    }

    public TextureRegion findRegion(String name) {
        return atlas.findRegion(name);
    }

    public Texture getPixel() {
        Pixmap map = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        map.drawPixel(1, 1, Color.WHITE.toIntBits());

        return new Texture(map);
    }

    public Texture getCircle(int pxRadius) {
        Pixmap map = new Pixmap(pxRadius * 2, pxRadius * 2, Pixmap.Format.RGBA8888);

        map.setColor(Color.WHITE);
        map.fillCircle(pxRadius, pxRadius, pxRadius);
        return new Texture(map);
    }

    public void load() {
        Fonts.load();
        Styles.load();
    }
}
