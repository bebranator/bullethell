package bullethell.graphics;

import bullethell.core.Core;
import bullethell.func.Cons;
import bullethell.func.Supp;
import bullethell.module.Styles;
import bullethell.module.Tex;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Shortcuts {
    public static void infoText(Supp<String> text) {
        CLabel label = new CLabel(text);

        CImage img = new CImage(Tex.black6);
        img.setWidth(400);
        img.setHeight(200);
        img.setAlign(Align.center);

        label.add();
        img.add();

        label.center();
        img.center();
        label.setZIndex(2);
        img.setZIndex(1);

        label.addAction(action());
        img.addAction(action());
    }

    static Action action() {
        return sequence(moveBy(0, 160, 1.0f, Interpolation.circleOut),
            delay(2.3f, sequence(alpha(0, 0.5f), removeActor()))
        );
    }

    public static void buttonsBox() {

    }
}
