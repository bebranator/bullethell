package bullethell.ui;

import bullethell.core.Core;
import bullethell.func.Cons;
import bullethell.graphics.CTable;
import bullethell.graphics.CWidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public interface Fragment {
    void build(CWidgetGroup target);

    default CTable table(Cons<CTable> tbl) {
        return Core.stage.table(tbl);
    }
}
