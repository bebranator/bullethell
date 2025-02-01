package bullethell.ui.dialog;

import bullethell.graphics.Draw;
import bullethell.graphics.Fill;
import bullethell.graphics.g2d.CTable;
import bullethell.log.Log;

import static bullethell.core.Vars.arena;

public class PauseDialog extends CDialog {

    public PauseDialog() {
        super("pause");

        shouldPause = true;
        shown(this::rebuild);
        tbl = table(e -> {
//            e.center().bottom();
            e.setBounds(0, 0, 800, 200);
        });
        tbl.setFillParent(false);
//        tbl.fill((x, y, w, h) -> {
//
//            Draw.color(1, 1, 1, .2f);
//            Draw.fill(x, y, w, h);
//        });
    }

    CTable tbl;
    void rebuild() {
        container.clearChildren();
    }

    @Override
    public void initTitle() {
    }
}
