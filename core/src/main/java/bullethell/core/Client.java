package bullethell.core;

import bullethell.content.*;
import bullethell.graphics.Fill;
import bullethell.graphics.g2d.CStage;
import bullethell.log.Log;
import bullethell.module.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static bullethell.core.Core.*;

public final class Client extends AbstractCore {
    /** Default application size. */
    public static final int WIDTH = 1200, HEIGHT = 900;
    public static final String TITLE = "The Void";

    @Override
    public void setup() {
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
        camera.position.set(WIDTH * .5f, HEIGHT * .5f, 0);

        atlas = new TextureAtlas("sprites.atlas");

        // content
        Content.init(
            new Bullets(),
            new PlayerTypes(),
            new Heroes()
        );

        // must have
        // assets
        Fonts.init();
        Styles.init();

        Vars.init();
        Tex.init();
        Sounds.init();


        // utils
        Fill.init();

        Core.input.setInputProcessor(new InputMultiplexer(stage, cinput));

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
}
