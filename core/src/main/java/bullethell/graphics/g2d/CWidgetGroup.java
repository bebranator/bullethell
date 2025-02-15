package bullethell.graphics.g2d;

import bullethell.core.Core;
import bullethell.func.Cons;
import bullethell.func.Pred0;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

import java.util.ConcurrentModificationException;

public class CWidgetGroup extends WidgetGroup {
    public void add() {
        Core.stage.add(this);
    }
    public void add(Actor... actors) {
        for(Actor a : actors) {
            addActor(a);
        }
    }

    public CTable table(Cons<CTable> tbl) {
        CTable table = new CTable();
        table.setFillParent(true);
        add(table);
        tbl.get(table);
        return table;
    }
}
