package bullethell.graphics;

import bullethell.core.Core;
import bullethell.func.Pred0;
import bullethell.func.Supp;
import bullethell.module.Styles;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import static bullethell.graphics.Shortcuts.*;

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
}
