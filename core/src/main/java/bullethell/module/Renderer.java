package bullethell.module;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.type.Bullet;
import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.utils.CPools;
import bullethell.utils.Textures;
import bullethell.utils.Time;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import static bullethell.core.Vars.*;

public class Renderer implements IModule {
    ShaderProgram test;
    Texture e, noise, libgdx;

    public Renderer() {
        test = new ShaderProgram(Core.files.internal("shaders/default.vert.glsl"), Core.files.internal("shaders/taiseiblackhole.frag.glsl"));
//        e = new Texture(Core.files.internal("bg_red.png"));
        libgdx = new Texture(Core.files.internal("libgdx.png"));
        noise = new Texture(Core.files.internal("noiseTexture.png"));
        e = Textures.cut(Core.assets.findRegion("bg_red"));
//        e.bind(0);
        e.bind(1);
        libgdx.bind(0);
        noise.bind(2);
        test.bind();
        test.setUniformMatrix("u_projTrans", Core.camera.view);
        test.setUniformi("u_texture", 0);
        test.setUniformi("u_texture0", 1);
        test.setUniformi("u_blend_mask", 2);
        test.setUniformf("u_resolution", new Vector2(Core.graphics.getWidth(), Core.graphics.getHeight()));
//        test.setUniformf("u_timemod", 1 / 240f);
        Core.graphics.getGL20().glActiveTexture(GL20.GL_TEXTURE0);
    }


    final Vector2 mouse = new Vector2(0, 0);
    final Vector2 bg_translation = new Vector2();
    // do test shaders
    @Override
    public void render() {
        ScreenUtils.clear(Color.BLACK);
        Batch batch = Core.batch;
        batch.begin();

        if(menu()) drawMenuBg();

        if(inGame() || paused()) {
            player.draw();
            enemyBullets.draw();
            Draw.text(Fonts.kelly24Outline, "alive bullets: " + Bullet.bulletCounter, 600, 600);
            Draw.text(Fonts.kelly24Outline, "pooled: " + CPools.get(Bullet.class, Bullet::new).getFree(), 600, 640);
            Draw.text(Fonts.kelly24Outline, "memory: " + Runtime.getRuntime().freeMemory(), 600, 680);
            Fill.rect(arena.viewport.x, arena.viewport.y, arena.viewport.width, arena.viewport.height);
        }
        batch.end();
    }

    void drawMenuBg() {
//        testMatrix.setToScaling(1, 1, 1f);
        bg_translation.set(Time.globalTime * 0.0006f, 0);

        Draw.color(1, 1, 1, 1);
        Draw.shader(test);
//        test.setUniformf("u_time", Time.globalTime);
        test.setUniformf("u_mouse", mouse);
        test.setUniformf("u_bg_trans", bg_translation);

        mouse.set(Core.input.getX(), Core.input.getY());

        Draw.fill(e, 0, 0, Core.camera.viewportWidth, Core.camera.viewportHeight);
        Draw.shader();

        Fill.circle(mouse.x, mouse.y, 64);
        Core.graphics.getGL20().glActiveTexture(GL20.GL_TEXTURE0);
    }
}
