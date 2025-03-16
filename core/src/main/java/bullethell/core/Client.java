package bullethell.core;

import bullethell.assets.Assets;
import bullethell.content.*;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.graphics.g2d.CStage;
import bullethell.log.Log;
import bullethell.module.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.profiling.GLErrorListener;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static bullethell.core.Core.*;

public final class Client extends AbstractCore {
    public static final int WIDTH = 1200, HEIGHT = 900;
    public static final int FPS = 60;
    public static final String TITLE = "gaming";

    @Override
    public void setup() {
        initSetup();

        Core.input.setInputProcessor(new InputMultiplexer(stage, cinput));
    }

    void initSetup() {
        Log.setupGdx();
        gl20 = Gdx.gl20;
        input = Gdx.input;
        files = Gdx.files;
        graphics = Gdx.graphics;
        app = Gdx.app;
        audio = Gdx.audio;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        stage = new CStage(viewport, batch);
        cinput = new CInput();

        Fill.init();

        Assets.init();
        Vars.init();

        AssetManager man = Assets.asset;

        man.load("sprites/error.png", Texture.class);
        Assets.loaded(() -> {
            Draw.init();
            Vars.postInit();
            Content.init(
                new Bullets(),
                new PlayerTypes()
            );
        });

        add(Vars.renderer = new Renderer());
        add(Vars.ui = new UI());
        add(Vars.control = new Control());
    }

    @Override
    public void render() {
        super.render();
        cinput.process();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void resume() {
        graphics.setSystemCursor(Cursor.SystemCursor.None);
    }
}
