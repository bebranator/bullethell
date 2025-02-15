package bullethell.graphics.action;

import bullethell.utils.CPools;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelFontScale extends LabelAction {
    public static LabelFontScale labelFontScale(float time, float targetX, float targetY) {
        LabelFontScale e = CPools.obtain(LabelFontScale.class, LabelFontScale::new);
        e.targetFontX = targetX;
        e.targetFontY = targetY;
        e.setDuration(time);
        return e;
    }
    public static LabelFontScale labelFontScale(float time, float target) {
        LabelFontScale e = CPools.obtain(LabelFontScale.class, LabelFontScale::new);
        e.targetFontX = target;
        e.targetFontY = target;
        e.setDuration(time);
        return e;
    }

    public LabelFontScale() {
        super(1);
    }

    public LabelFontScale(float time, float targetFontX, float targetFontY) {
        super(time);
        this.targetFontX = targetFontX;
        this.targetFontY = targetFontY;
    }
    public LabelFontScale(float time, float targetFont) {
        super(time);
        this.targetFontX = this.targetFontY = targetFont;
    }

    private float targetFontX, targetFontY;
    private float originalX, originalY;

    @Override
    protected void begin() {
        // assume font scale x is original
        this.originalX = getLabel().getFontScaleX(); // why tho
        this.originalY = getLabel().getFontScaleY(); // why tho
    }

    @Override
    protected void update(float v) {
        Label target = getLabel();

        target.setFontScale(Interpolation.linear.apply(originalX, targetFontX, v),
            Interpolation.linear.apply(originalY, targetFontY, v));
    }
}
