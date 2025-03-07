package bullethell.core;

import bullethell.graphics.g2d.CStage;
import bullethell.module.Asset;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Core {
    public static Graphics graphics;
    public static CStage stage;
    public static Batch batch;
    public static Files files;
    public static Input input;
    public static GL20 gl20;
    public static Audio audio;
    public static Viewport viewport;
    // cirno input
    public static CInput cinput;
    public static Application app;
    public static Camera camera;
    public static TextureAtlas atlas;

    public static void panic(String reason) {
        throw new RuntimeException(reason);
//        Core.app.exit();
    }
    public static void panic(String reason, Throwable th) {
        throw new RuntimeException(reason, th);
    }
}
