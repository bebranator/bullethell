package bullethell.core;

import bullethell.graphics.CStage;
import bullethell.module.Asset;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Core {
    public static Graphics graphics;
    public static CStage stage;
    public static Batch batch;
    public static Files files;
    public static Input input;
    public static GL20 gl20;
    // cirno input
    public static CInput cinpit;
    public static Application app;
    public static Camera camera;
    public static Asset assets;
}
