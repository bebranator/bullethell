package bullethell.core;

import bullethell.graphics.CStage;
import bullethell.log.DefaultLoggerProvider;
import bullethell.log.Log;
import bullethell.module.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.logging.Logger;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public final class Client extends AbstractCore {
    /** Default application size. */
    public static final int WIDTH = 1920, HEIGHT = 1080;
    public static final String TITLE = "One night at Bazarov build 126";
    @Override
    public void setup() {
//        Core.stage = new CStage(new ExtendViewport(WIDTH, HEIGHT, new OrthographicCamera()), new SpriteBatch());
//        Core.batch = Core.stage.getBatch();
//        Core.camera = Core.stage.getCamera();
        Core.input = Gdx.input;
        Core.files = Gdx.files;
        Core.graphics = Gdx.graphics;
        Core.app = Gdx.app;
        Core.assets = new Asset();
        Core.stage = new CStage(new ExtendViewport(WIDTH, HEIGHT, new OrthographicCamera()), new SpriteBatch());
        Core.batch = Core.stage.getBatch();
        Core.camera = Core.stage.getCamera();

        Vars.init();
        Tex.init();

        Core.input.setInputProcessor(Core.stage);

        Log.setLogger(new DefaultLoggerProvider());

//        Vars.assets = new Asset();
//        Vars.assets.load();
//        Vars.renderer = new Renderer();
//        Vars.ui = new UI();
        add(Vars.renderer = new Renderer());
        add(Vars.ui = new UI());
        add(Vars.control = new Control());
    }
}
