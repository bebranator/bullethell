package bullethell.graphics.action;

import bullethell.utils.StringInterpolator;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelWriteAction extends TemporalAction {
    private StringInterpolator interpolator = new StringInterpolator();
    private String targetText;

    public LabelWriteAction(String targetText) {
        this(4, targetText);
    }
    public LabelWriteAction(float time, String targ) {
        super(time);
        this.targetText = targ;
    }

    @Override
    protected void update(float progress) {
        Label target = (Label) getTarget();

        interpolator.setProgress(progress);
        interpolator.setTargetTime(getDuration());
        interpolator.setString(targetText);

        target.setText(interpolator.get());
    }
}
