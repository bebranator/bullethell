package bullethell.graphics.g2d;

import bullethell.core.Core;
import bullethell.func.Supp;
import bullethell.module.Styles;
import bullethell.utils.Tmp;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

// todo: draw rotated
public class CLabel extends Label {
    public Supp<String> supplier;

    public CLabel(Supp<String> supplier, LabelStyle style) {
        super(supplier.get(), style);
        this.supplier = supplier;
    }
    public CLabel(Supp<String> supplier) {
        this(supplier, Styles.defLabel);
    }
    public CLabel() {
        this(() -> "", Styles.defLabel);
    }

    public void add() {
        Core.stage.add(this);
    }

    public void center() {
        Stage stage = getStage();

        Vector2 position = Tmp.v21.set((stage.getWidth() - getWidth()) / 2, (stage.getHeight() - getHeight()) / 2);

        setPosition(position.x, position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
    public void nullSupplier() {
        this.supplier = null;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(supplier == null) return;
        setText(supplier.get());
    }

    public void changeText(String text) {
        supplier = () -> text;
    }
}
