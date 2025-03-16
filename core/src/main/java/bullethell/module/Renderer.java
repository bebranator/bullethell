package bullethell.module;

import bullethell.assets.Assets;
import bullethell.core.Client;
import bullethell.core.Core;
import bullethell.core.Settings;
import bullethell.core.Vars;
import bullethell.entity.type.Bullet;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.utils.CPools;
import bullethell.utils.Time;
import bullethell.utils.Tmp;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;

import java.nio.FloatBuffer;

import static bullethell.core.Core.*;
import static bullethell.core.Vars.*;

public class Renderer implements IModule {
    private ShaderProgram blackHole;
    private Texture backgroundRed, noise;
    private FrameBuffer arenaBuffer;
    private final Matrix4 arenaViewport = new Matrix4();
    private final Vector2 bg_translation = new Vector2();
    private final Affine2 arenaTransformation2D = new Affine2();

    public Renderer() {
        backgroundRed = new Texture(Core.files.internal("sprites/bg_red.png"));
        noise = new Texture(Core.files.internal("sprites/noiseTexture.png"));

        Assets.shader("shaders/default.vert", "shaders/black_hole.frag");
        Assets.loaded(this::init);
        arenaBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) arena.viewport.width,
            (int) arena.viewport.height, true);
        recalculateTransformation();
    }

    void init() {
//        blackHole = new ShaderProgram(Core.files.internal("shaders/default.vert.glsl"), Core.files.internal("shaders/taiseiblackhole.frag.glsl"));
//
        blackHole = Assets.shader("shaders/black_hole.frag");
        backgroundRed.bind(2);
        noise.bind(1);

        blackHole.bind();
        blackHole.setUniformMatrix("u_projTrans", Core.camera.view);
        blackHole.setUniformi("u_texture0", 2);
        blackHole.setUniformi("u_blend_mask", 1);
        blackHole.setUniformf("u_resolution", new Vector2(Client.WIDTH, Client.HEIGHT));
        Core.graphics.getGL20().glActiveTexture(GL20.GL_TEXTURE0);
    }

    @Override
    public void render() {
        gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl20.glClearColor(0, 0, 0, 1);

        Draw.begin();
        Fill.begin();
        Fill.proj(camera.combined);

        if(!Assets.loaded) {
            drawLoading();
        }
        else {
            if (menu()) drawMenuBg();

            if (inGame() || paused()) {
                Draw.transform(arenaViewport);

                arenaBuffer.begin();

                gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
                items.draw();
                enemyBullets.draw();
                playerBullets.draw();
                lasers.draw();
                enemies.draw();
                player.draw();

                Draw.flush();
                Draw.transform();

                arenaBuffer.end(viewport.getScreenX(), viewport.getScreenY(),
                    viewport.getScreenWidth(), viewport.getScreenHeight());

                Draw.draw(arenaBuffer.getColorBufferTexture(), arena.viewport.x, arena.viewport.y,
                    arena.viewport.width, arena.viewport.height, false, true);

                Draw.color();
                Fill.line();
                Fill.rect(arena.viewport.x, arena.viewport.y, arena.viewport.width, arena.viewport.height);

                Draw.color();
                game.levelDraw();
            }
            if(Settings.debug) drawDebug();
        }

        Draw.end();
        Fill.end();
    }

    void drawLoading() {
        float prog = Assets.progress;
        Fill.filled();
        Fill.color(Color.RED);
        Fill.rect(200, 200, 800 * prog, 200);
    }

    void drawMenuBg() {
        bg_translation.set(Time.globalTime * 0.0006f, 0);

        Draw.color();
        Draw.shader(blackHole);
        blackHole.setUniformf("R", 40);
        blackHole.setUniformf("u_bg_trans", bg_translation);
        Draw.drawc(backgroundRed);
        Draw.shader();
    }
    Pool<Bullet> bulletPool = CPools.get(Bullet.class, Bullet::new);

    void drawDebug() {
        Draw.textMode();
        text("stage: " + game.level, 20, 20);
        text("state: " + Vars.state(), 20, 40);
        text("pcoord: " + player.position(), 20, 60);
        text("BulletPool: " + bulletPool.getFree() + "; " + Bullet.bulletCounter, 20, 80);
        if(game.level != null) {
            text(game.level.debug(), 20, Client.HEIGHT, Align.left | Align.top);

            if(game.level.current() != null) {
                text("attack: " + game.level.current(), 20, Client.HEIGHT - 100);
                text("attack debug: " + game.level.current().debug(), 20, Client.HEIGHT - 200, Align.left | Align.top);
            }
        }
        Draw.textEnd();
    }
    void drawGameDebug() {
//        Fill.transform();
//        Fill.scale(.5f, .5f);
//        Fill.translate(Client.WIDTH / 2, Client.HEIGHT / 2);
        Fill.color(Color.GREEN);
        Fill.filled();
        Fill.circle(player.getX(), player.getY(), player.getSize());
        //        Fill.translate(-arena.viewport.x, -arena.viewport.y);
        Fill.line();
        enemies.forEach(e -> {
            Fill.rect(e.getX(), e.getY(), e.getSize(), e.getSize());
            Fill.line(e.getX(), e.getY(), Tmp.v21.set(e.params().velocity).add(e.getX(), e.getY()));
        });
        enemyBullets.forEach(e -> {
            Fill.circle(e.getX(), e.getY(), e.getSize());
            Fill.line(e.getX(), e.getY(), Tmp.v21.set(e.params().velocity).add(e.getX(), e.getY()));
        });
        Fill.flush();
//        Fill.transform();

//        Fill.translate(arena.viewport.x, arena.viewport.y);
    }
    public void resizeBuffer(int w, int h) {
        arenaBuffer.dispose();

        arenaBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, w, h, true);
        recalculateTransformation();
    }
    protected void recalculateTransformation() {
        arenaTransformation2D.idt();
        Rectangle viewport = arena.viewport;
        // tf FIXME
        // get rid of viewport's coordinates
//        arenaTransformation2D.translate(-viewport.x - player.getSize() * 4, -viewport.y - player.getSize());
        arenaTransformation2D.scale(Client.WIDTH / viewport.width, Client.HEIGHT / viewport.height);
        arenaViewport.set(arenaTransformation2D);
    }

    void text(String text, float x, float y) {
        Draw.text(Fonts.kelly12, text, x, y, 0, Align.left);
    }
    void text(String text, float x, float y, int align) {
        Draw.text(Fonts.kelly12, text, x, y, 0, align);
    }
}
