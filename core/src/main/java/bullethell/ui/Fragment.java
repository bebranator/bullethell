package bullethell.ui;

import bullethell.core.Core;
import bullethell.func.Cons;
import bullethell.graphics.g2d.CTable;
import bullethell.graphics.g2d.CWidgetGroup;

public interface Fragment {
    void build(CWidgetGroup target);

    default CTable table(Cons<CTable> tbl) {
        CTable table = new CTable();
        table.setFillParent(true);

        tbl.get(table);
        return table;
    }
}
