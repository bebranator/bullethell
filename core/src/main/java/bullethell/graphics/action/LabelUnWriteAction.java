package bullethell.graphics.action;

import bullethell.utils.StringInterpolator;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelUnWriteAction extends TemporalAction {
    private String originalText;
    private StringInterpolator interp;

    public LabelUnWriteAction(float time) {
        super(time);
    }

    @Override
    protected void begin() {
        super.begin();
        originalText = ((Label)getTarget()).getText().toString();
    }

    @Override
    protected void update(float v) {
        Label label = (Label) getTarget();

        interp.setProgress(1 - v);
        interp.setString(originalText);
        interp.setTargetTime(getDuration());
        label.setText(interp.get());
    }
}
