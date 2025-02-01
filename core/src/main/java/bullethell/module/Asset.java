package bullethell.module;

import bullethell.core.Core;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Asset {
    private AssetManager assetManager;

    private TextureAtlas atlas;

    public Asset() {
        assetManager = new AssetManager();
    }

    public TextureRegion findRegion(String name) {
        return atlas.findRegion(name);
    }

    public void load() {
//        Fonts.load();
//        Styles.load();
    }
}
