package bullethell.graphics;

import bullethell.core.Core;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Align;

import static bullethell.core.Core.*;

public class Draw {
    public static int textModeCalls = 0;
    public static TextureRegion white = Core.atlas.findRegion("pixel"),
        circle = Core.atlas.findRegion("circle50");
    private static boolean textMode = false;

    private static final float[] vertices = new float[6 * 4];

    public static void batch(Batch nextBatch){
        batch.flush();
        Core.batch = nextBatch;
    }

    public static void batch(Batch nextBatch, Runnable run) {
        Batch prev = batch;
        prev.flush();

        batch = nextBatch;

        run.run();

        nextBatch.flush();
        batch = prev;
    }

    public static void fbo(FrameBuffer buffer) {
        fill(buffer.getColorBufferTexture(), camera.position.x, camera.position.y, camera.viewportWidth, camera.viewportHeight);
    }

    public static void shader(ShaderProgram program) {
        batch.setShader(program);
    }

    public static void shader() {
        batch.setShader(null);
    }

    public static void color(float r, float g, float b, float a) {
        batch.setColor(r, g, b, a);
    }
    public static void color(Color color) {
        batch.setColor(color);
    }
    public static void color() {
        batch.setColor(Color.WHITE);
    }

    public static void proj(Matrix4 matrix) {
        batch.setProjectionMatrix(matrix);
    }

    public static void proj() {
        batch.setProjectionMatrix(camera.combined);
    }

    public static void fill() {
        fill(white, 0, 0, Core.camera.viewportWidth, Core.camera.viewportHeight);
    }

    public static void fill(float x, float y, float w, float h) {
        batch.draw(white, x, y, w, h);
    }

    public static void fill(TextureRegion region, float x, float y, float w, float h) {
        batch.draw(region, x, y, w, h);
    }
    public static void fill(Texture region, float x, float y, float w, float h) {
        batch.draw(region, x, y, w, h);
    }

    /*
    draw (TextureRegion region, float x, float y, float originX, float originY, float width,
        float height, float scaleX, float scaleY, float rotation)
     */
    public static void fill(TextureRegion region, float x, float y, float w, float h, float rotationRadian) {
        batch.draw(region, x, y, 0, 0, w, h, 1, 1, rotationRadian);
    }

    public static void fill(float x, float y, float w, float h, float rotationRadian) {
        batch.draw(white, x, y, 0, 0, w, h, 1, 1, rotationRadian);
    }

    // todo: fix circle draw
    /*
    public void draw (TextureRegion region, float x, float y, float originX, float originY, float width, float height,
		float scaleX, float scaleY, float rotation)
     */
    public static void draw(TextureRegion t, float x, float y, float originX, float originY, float width, float height,
        float scaleX, float scaleY, float rotation) {
//        batch.draw();
        batch.draw(t, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
    }

    public static void draw(TextureRegion t, float x, float y, float width, float height) {
        batch.draw(t, x, y, width, height);
    }

    // trying solution
    public static void textMode() {
        if(textMode) throw new IllegalStateException("Already running text modo!");
        textMode = true;

        batch.flush();
        batch.end();
        batch.begin();

        textModeCalls++;
    }

    public static void textEnd() {
        if(!textMode) throw new IllegalStateException("Didn't drawn any of text!");
        textMode = false;

        batch.end();
        batch.begin();
    }

    public static void text(BitmapFont font, String text, float x, float y) {
        font.draw(batch, text, x, y, 0f, Align.center, false);
//        font.draw()
    }

    public static void quad(TextureRegion region, float x1, float y1, float c1, float x2, float y2, float c2, float x3, float y3, float c3, float x4, float y4, float c4){
        float mcolor = Core.batch.getPackedColor();
        float u = region.getU();
        float v = region.getV2();
        float u2 = region.getU2();
        float v2 = region.getV();
        vertices[0] = x1;
        vertices[1] = y1;
        vertices[2] = c1;
        vertices[3] = u;
        vertices[4] = v;
        vertices[5] = mcolor;

        vertices[6] = x2;
        vertices[7] = y2;
        vertices[8] = c2;
        vertices[9] = u;
        vertices[10] = v2;
        vertices[11] = mcolor;

        vertices[12] = x3;
        vertices[13] = y3;
        vertices[14] = c3;
        vertices[15] = u2;
        vertices[16] = v2;
        vertices[17] = mcolor;

        vertices[18] = x4;
        vertices[19] = y4;
        vertices[20] = c4;
        vertices[21] = u2;
        vertices[22] = v;
        vertices[23] = mcolor;

        Draw.vert(region.getTexture(), vertices, 0, vertices.length);
    }

    public static void vert(Texture texture, float[] vertices, int offset, int length){
        Core.batch.draw(texture, vertices, offset, length);
    }

    public static void begin() {
        batch.begin();
    }

    public static void end() {
        batch.end();
    }
}
