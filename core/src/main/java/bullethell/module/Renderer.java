package bullethell.module;

import bullethell.core.Client;
import bullethell.core.Core;
import bullethell.core.Settings;
import bullethell.core.Vars;
import bullethell.entity.type.Bullet;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.utils.CPool;
import bullethell.utils.CPools;
import bullethell.utils.E;
import bullethell.utils.Time;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;

import static bullethell.core.Core.*;
import static bullethell.core.Vars.*;

public class Renderer implements IModule {
    ShaderProgram blackHole;
    Texture e, noise, libgdx;
    private Matrix4 arenaViewport;
    private FrameBuffer arenaBuffer;

    public Renderer() {
        e = new Texture(Core.files.internal("bg_red.png"));
        libgdx = new Texture(Core.files.internal("libgdx.png"));
        noise = new Texture(Core.files.internal("noiseTexture.png"));

        blackHole = new ShaderProgram(Core.files.internal("shaders/default.vert.glsl"), Core.files.internal("shaders/taiseiblackhole.frag.glsl"));

        e.bind(2);
        noise.bind(1);

        blackHole.bind();
        blackHole.setUniformMatrix("u_projTrans", Core.camera.view);
        blackHole.setUniformi("u_texture0", 2);
        blackHole.setUniformi("u_blend_mask", 1);
        blackHole.setUniformf("u_resolution", new Vector2(Client.WIDTH, Client.HEIGHT));
//        test.setUniformf("u_timemod", 1 / 240f);
        Core.graphics.getGL20().glActiveTexture(GL20.GL_TEXTURE0);

        arenaViewport = new Matrix4();
        arenaBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, (int) arena.viewport.width,
            (int) arena.viewport.height, true);
    }

    final Vector2 bg_translation = new Vector2();
    // do test shaders
    @Override
    public void render() {
        Batch batch = Core.batch;
        ShapeRenderer shapes = Fill.shapes;

        gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl20.glClearColor(0, 0, 0, 1);

        shapes.setProjectionMatrix(camera.combined);
        batch.begin();
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setAutoShapeType(true);

        if (menu()) drawMenuBg();

        if (inGame() || paused()) {
            arenaViewport.setToScaling(Client.WIDTH / arena.viewport.width, Client.HEIGHT / arena.viewport.height, 0);
            arenaViewport.setTranslation(-arena.viewport.x, -arena.viewport.y, 0); // normalize coordinates
            Draw.transform(arenaViewport);

            arenaBuffer.begin();

            gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        shapes.end();
        batch.end();
    }

    void drawMenuBg() {
        bg_translation.set(Time.globalTime * 0.0006f, 0);

        Draw.color();
        Draw.shader(blackHole);
        blackHole.setUniformf("R", 40);
        blackHole.setUniformf("u_bg_trans", bg_translation);
        Draw.fill(Tex.tblack);
        Draw.shader();
    }
    Pool<Bullet> bulletPool = CPools.get(Bullet.class, Bullet::new);

    void drawDebug() {
        Draw.textMode();
        text("stage: " + game.level, 20, 20);
        text("state: " + Vars.state(), 20, 40);
        text("pcoord: " + player.position(), 20, 60);
        text("BulletPool: " + bulletPool.peak + "; " + Bullet.bulletCounter, 20, 80);
        if(game.level != null && game.level.current() != null) {
            text(game.level.debug(), 20, Client.HEIGHT, Align.left | Align.top);

            text("attack: " + game.level.current(), 20, Client.HEIGHT - 100);
            text("attack debug: " + game.level.current().debug(), 20, Client.HEIGHT - 200, Align.left | Align.top);
        }
        Draw.textEnd();
    }
    void text(String text, float x, float y) {
        Draw.text(Fonts.kelly12, text, x, y, 0, Align.left);
    }
    void text(String text, float x, float y, int align) {
        Draw.text(Fonts.kelly12, text, x, y, 0, align);
    }
    public void resizeBuffer(int w, int h) {
        arenaBuffer.dispose();

        arenaBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, w, h, true);
    }
}
