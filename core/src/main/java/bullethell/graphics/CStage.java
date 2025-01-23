package bullethell.graphics;

import bullethell.func.Cons;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CStage extends Stage {
    public CStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
    }

    public CTable table(Cons<CTable> table) {
        CTable t = new CTable();
        t.setFillParent(true);
        addActor(t);
        table.get(t);

        return t;
    }

    public void add(Actor... actors) {
        for(Actor c: actors) {
            addActor(c);
        }
    }
}
