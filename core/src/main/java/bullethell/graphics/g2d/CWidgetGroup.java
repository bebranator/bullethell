package bullethell.graphics.g2d;

import bullethell.core.Core;
import bullethell.func.Pred0;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class CWidgetGroup extends WidgetGroup {
    public Pred0 visibility = () -> true;

    @Override
    public void act(float delta) {
        super.act(delta);
        setVisible(visibility.get());
    }

    public void visible(Pred0 visibility) {
        this.visibility = visibility;
    }
    public void add() {
        Core.stage.add(this);
    }
}
