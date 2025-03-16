package bullethell.assets;

import bullethell.core.Client;
import bullethell.core.Core;
import bullethell.log.Log;
import bullethell.module.Tex;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ShaderProgramLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;

public class Assets {
    public static AssetManager asset;

    public static TextureRegion white, error;

    public static int updateMillis = 1000 / Client.FPS;
    public static boolean loaded;
    public static float progress;
    private static final Array<Runnable> runnables = new Array<>();

    public static void init() {
        asset = new AssetManager(new InternalFileHandleResolver(), true);
        asset.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(Core.files::internal));
    }

    public static void postInit() {
        Log.info("Loaded!");
        Core.atlas = asset.get("sprites/sprites.atlas", TextureAtlas.class);

        white = findRegion("white");

        runnables.forEach(Runnable::run);
    }
    public static boolean update() {
        progress = asset.getProgress();
        return asset.update(updateMillis);
    }

    public static void loaded(Runnable listener) {
        runnables.add(listener);
    }

    public static void shader(String vert, String frag) {
        ShaderProgramLoader.ShaderProgramParameter param = new ShaderProgramLoader.ShaderProgramParameter();
        param.fragmentFile = frag;
        param.vertexFile = vert;
        param.logOnCompileFailure = true;

        asset.load(frag, ShaderProgram.class, param);
    }
    public static <T> void load(String name, Class<T> clazz) {
        asset.load(name, clazz);
    }
    public static <T> void load(String name, Class<T> clazz, AssetLoaderParameters<T> params) {
        asset.load(name, clazz, params);
    }
    public static <T> T get(String name, Class<T> clazz) {
        return asset.get(name, clazz, true);
    }
    public static TextureRegion findRegion(String name) {
        TextureRegion reg;
        return (reg = Core.atlas.findRegion(name)) == null ? error : reg;
    }
}
