package bullethell.ui.dialog;

import bullethell.core.Core;
import bullethell.graphics.Draw;
import bullethell.graphics.g2d.CTable;
import bullethell.module.Tex;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

// fun but fine
public class DialogueDialog extends CDialog {
    private Image dialogueBg;

    public DialogueDialog() {
        super("dialogue");

        shown(this::rebuild);
        hidden(this::hidden);
        shouldPause = true;

        dialogueBg = new Image(Core.atlas.findRegion("white"));
        dialogueBg.setColor(Color.BLACK);
        dialogueBg.setBounds(0, 0, 720, 200);
        dialogueBg.setScale(1, .3f);
        add(dialogueBg);
    }

    void hidden() {
        dialogueBg.addAction(scaleTo(1, 0f, 1f));
    }

    void rebuild() {
        dialogueBg.addAction(scaleTo(1, 1, 1f));
    }

    // don't do it
    @Override
    public void initTitle() {
    }
}
