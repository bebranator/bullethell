package bullethell.core;

import bullethell.content.Bullets;
import bullethell.content.Sounds;
import bullethell.graphics.Fill;
import bullethell.graphics.g2d.CStage;
import bullethell.log.Log;
import bullethell.module.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static bullethell.core.Core.*;
import static bullethell.graphics.Fill.*;

public final class Client extends AbstractCore {
    /** Default application size. */
    public static final int WIDTH = 1200, HEIGHT = 900;
    public static final String TITLE = "The Void";

    private boolean finished;
    private AssetManager assets;

    @Override
    public void setup() {
        finished = false;
//        Core.stage = new CStage(new ExtendViewport(WIDTH, HEIGHT, new OrthographicCamera()), new SpriteBatch());
//        Core.batch = Core.stage.getBatch();
//        Core.camera = Core.stage.getCamera();
        Log.setup();
        gl20 = Gdx.gl20;
        input = Gdx.input;
        files = Gdx.files;
        graphics = Gdx.graphics;
        app = Gdx.app;
        audio = Gdx.audio;
        batch = new SpriteBatch();
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        stage = new CStage(new FitViewport(WIDTH, HEIGHT, camera), batch);
        cinput = new CInput();
        camera.position.set(WIDTH * .5f, HEIGHT * .5f, 0);

        atlas = new TextureAtlas("sprites.atlas");

        assets = new AssetManager();

//        AssetLoaderParameters<TextureAtlas> atls = new AssetLoaderParameters<>();
//        atls.loadedCallback = (t, a, e) -> {
//            atlas = t.get("sprites.atlas");
//        };
//        assets.load(new AssetDescriptor<>("sprites.atlas", TextureAtlas.class, atls));


//        // must have
        Vars.init();
        Tex.init();
        Sounds.init();

        // content
        Bullets.init();

        // utils
        Fill.init();

        Core.input.setInputProcessor(new InputMultiplexer(stage, cinput));

//        Vars.assets = new Asset();
//        Vars.assets.load();
//        Vars.renderer = new Renderer();
//        Vars.ui = new UI();
        add(Vars.renderer = new Renderer());
        add(Vars.ui = new UI());
        add(Vars.control = new Control());
    }

    @Override
    public void render() {
//        if(assets.update(1000 / 60)) {
//            finished = true;
//            return;
//        }
//
//        if(finished) {
            super.render();
            cinput.process();
//        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }
}
