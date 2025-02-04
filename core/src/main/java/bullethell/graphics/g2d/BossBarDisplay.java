package bullethell.graphics.g2d;

import bullethell.graphics.Draw;
import bullethell.module.Fonts;
import bullethell.utils.Time;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class BossBarDisplay extends Actor {
    private float progress = 1f; // clamped between 0 and 1
    private float tempProgress = 1f;
    private float maxStep = .04f;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float w = getWidth() * tempProgress;

        Draw.color(Color.BLUE);
        Draw.draw(getX(), getY(), getWidth(), getHeight());
        Draw.color(getColor());
        Draw.draw(getX(), getY(), w, getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // change a little till become progress
        tempProgress = MathUtils.clamp(tempProgress + maxStep * Math.signum(progress - tempProgress), 0, 1);
    }

    public void setProgress(float newValue) {
        this.progress = newValue;
    }
}
