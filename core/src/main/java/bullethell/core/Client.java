package bullethell.core;

import bullethell.graphics.CStage;
import bullethell.log.DefaultLoggerProvider;
import bullethell.log.Log;
import bullethell.module.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static bullethell.core.Core.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public final class Client extends AbstractCore {
    /** Default application size. */
    public static final int WIDTH = 800, HEIGHT = 600;
    public static final String TITLE = "One night at Bazarov build 146";
    @Override
    public void setup() {
//        Core.stage = new CStage(new ExtendViewport(WIDTH, HEIGHT, new OrthographicCamera()), new SpriteBatch());
//        Core.batch = Core.stage.getBatch();
//        Core.camera = Core.stage.getCamera();
        Log.setup();
        input = Gdx.input;
        files = Gdx.files;
        graphics = Gdx.graphics;
        app = Gdx.app;
        assets = new Asset();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        stage = new CStage(new FitViewport(WIDTH, HEIGHT, camera), batch);

        Vars.init();
        Tex.init();

        Core.input.setInputProcessor(Core.stage);

//        Vars.assets = new Asset();
//        Vars.assets.load();
//        Vars.renderer = new Renderer();
//        Vars.ui = new UI();
        add(Vars.renderer = new Renderer());
        add(Vars.ui = new UI());
        add(Vars.control = new Control());
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }
}
