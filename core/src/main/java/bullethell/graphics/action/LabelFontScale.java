package bullethell.graphics.action;

import bullethell.utils.CPools;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelFontScale extends LabelAction {
    public static LabelFontScale labelFontScale(float time, float target) {
        LabelFontScale e = CPools.obtain(LabelFontScale.class, LabelFontScale::new);
        e.targetFont = target;
        e.setDuration(time);
        return e;
    }

    public LabelFontScale() {
        super(1);
    }

    public LabelFontScale(float time, float targetFont) {
        super(time);
    }

    private float targetFont;
    private float original;

    @Override
    protected void begin() {
        // assume font scale x is original
        this.original = getLabel().getFontScaleX(); // why tho
    }

    @Override
    protected void update(float v) {
        Label target = getLabel();

        target.setFontScale(Interpolation.linear.apply(original, targetFont, v));
    }
}
