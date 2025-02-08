package bullethell.graphics.action;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public abstract class LabelAction extends TemporalAction {
    LabelAction(float time) {
        super(time);
    }

    public Label getLabel() {
        return (Label) getTarget();
    }
}
