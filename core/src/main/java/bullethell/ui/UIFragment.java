package bullethell.ui;

import bullethell.graphics.g2d.CTable;
import bullethell.graphics.g2d.CWidgetGroup;
import com.badlogic.gdx.utils.Align;

// todo: show boss health, spell card visuals
public class UIFragment implements Fragment {
    private CTable rightTab;

    @Override
    public void build(CWidgetGroup target) {
        rightTab = table((e) -> {
            e.center();
            e.align(Align.right);
            e.label(() -> "DEBUG MODE");
        });
        rightTab.visibility = target.visibility;
    }
}
