package bullethell.module;

import bullethell.core.Client;
import bullethell.core.Core;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.utils.Time;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.Vector2;

import static bullethell.core.Core.*;
import static bullethell.core.Vars.*;

public class Renderer implements IModule {
    ShaderProgram blackHole;
    Texture e, noise, libgdx;

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
    }

    final Vector2 mouse = new Vector2(0, 0);
    final Vector2 bg_translation = new Vector2();
    // do test shaders
    @Override
    public void render() {
        Batch batch = Core.batch;
        ShapeRenderer shapes = Fill.shapes;

        gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl20.glClearColor(0, 0, 0, 1);

        shapes.setProjectionMatrix(Core.camera.combined);
        batch.begin();
        shapes.begin(ShapeRenderer.ShapeType.Line);
        shapes.setAutoShapeType(true);

        if (menu()) drawMenuBg();

        if (inGame() || paused()) {

            Draw.flush();
            enemyBullets.draw();
            playerBullets.draw();
            lasers.draw();
            healthEntities.draw();
            player.draw();

            Draw.flush();
            Draw.color();
            Fill.line();
            Fill.rect(arena.viewport.x, arena.viewport.y, arena.viewport.width, arena.viewport.height);
            Draw.color();

            game.levelDraw();
        }
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
}
