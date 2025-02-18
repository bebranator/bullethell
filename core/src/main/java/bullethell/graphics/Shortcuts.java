package bullethell.graphics;

import bullethell.core.Core;
import bullethell.func.Supp;
import bullethell.graphics.action.LabelFontScale;
import bullethell.graphics.g2d.CImage;
import bullethell.graphics.g2d.CLabel;
import bullethell.graphics.g2d.CTable;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.module.Styles;
import bullethell.module.Tex;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static bullethell.core.Vars.*;

public class Shortcuts {
    public static void infoText(Supp<String> text) {
        CWidgetGroup infoText = new CWidgetGroup();
        CLabel label = new CLabel(text);
        CImage image = new CImage(Tex.black6);
        image.setBounds(-200, -50, 400, 100);
        label.setX(0, Align.bottom);

        infoText.addActor(image);
        infoText.addActor(label);
        infoText.setPosition(Core.stage.getWidth() / 2, 0, Align.center | Align.bottom);
        infoText.add();

        infoText.addAction(sequence(moveBy(0, 160, 1.0f, Interpolation.circleOut),
            delay(2.3f, sequence(alpha(0, 0.5f), removeActor()))
        ));
    }

    public static void stage(String baseText) {
        Label baseTextLabel = new Label(baseText, Styles.defLabel);
        Core.stage.add(baseTextLabel);
        baseTextLabel.setVisible(false);

        baseTextLabel.setFontScaleY(0.001f);
        baseTextLabel.setPosition(Core.stage.getWidth() / 2, Core.stage.getHeight() / 2, Align.center);
        baseTextLabel.setAlignment(Align.bottom, Align.center);


        baseTextLabel.addAction(sequence(
            parallel(
                show(),
                LabelFontScale.labelFontScale(1, Interpolation.fastSlow, 1, 1)
            ),
            delay(3.2f,
                sequence(
                    LabelFontScale.labelFontScale(1, Interpolation.slowFast, 1, 0.001f),
                    hide()
                )
            )
        ));
    }

    public static void arenaNotification(String note) {
        if(paused() || inGame()) {
            CLabel label = new CLabel(() -> note);
            label.setOrigin(Align.bottom | Align.right);
            float h = label.getHeight();
            float x = arena.world.x + arena.world.width - label.getWidth();
            float y = arena.world.y - h * 3;
            label.setPosition(x, y);
            label.addAction(
                sequence(
                    moveTo(x, y + h * 3, .2f),
                    delay(4f, sequence(moveTo(
                        x, y, .2f
                    ), removeActor()))
                )
            );
            label.add();
        }
    }

    public static void buttonsBox() {

    }
}
