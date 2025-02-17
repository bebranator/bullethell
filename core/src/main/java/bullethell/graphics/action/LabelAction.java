package bullethell.graphics.action;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public abstract class LabelAction extends TemporalAction {
    LabelAction(float time) {
        super(time);
    }
    LabelAction(float time, Interpolation interp) {
        super(time, interp);
    }

    public Label getLabel() {
        return (Label) getTarget();
    }
}
