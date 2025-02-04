package bullethell.graphics.g2d;

import bullethell.core.Core;
import bullethell.func.Cons4;
import bullethell.func.Pred0;
import bullethell.func.Supp;
import bullethell.module.Styles;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class CTable extends Table {
    public Pred0 visibility = () -> true;

    public Cell<CLabel> label(Supp<String> string, Label.LabelStyle style) {
        return add(new CLabel(string, style));
    }

    public Cell<CLabel> label(Supp<String> string) {
        return label(string, Styles.defLabel);
    }
    public Cell<Image> image(Drawable draw) {
        return add(new Image(draw));
    }
    public Cell<Image> image(TextureRegion region) {
        return add(new Image(region));
    }

    public void addToScene() {
        Core.stage.add(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setVisible(visibility.get());
    }

    public void fill(Cons4<Float, Float, Float, Float> draw) {
        Actor act = new Actor() {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                draw.get(CTable.this.getX(),
                    CTable.this.getY(),
                    CTable.this.getWidth(),
                    CTable.this.getHeight());
            }
        };
        add(act);
    }
    public void update(Runnable updater) {
        Actor upd = new Actor() {
            @Override
            public void act(float delta) {
                updater.run();
            }
        };
        add(upd);
    }
}
