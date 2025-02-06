package bullethell.ui;

import bullethell.core.Core;
import bullethell.graphics.action.LabelWriteAction;
import bullethell.graphics.g2d.CImage;
import bullethell.graphics.g2d.CLabel;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.module.Styles;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

// todo: show boss health, spell card visuals
public class UIFragment implements Fragment {
    private CWidgetGroup spellPortraitGroup;
    private CImage spellPortrait;

    private Label spellName;
    @Override
    public void build(CWidgetGroup target) {
        spellPortraitGroup = new CWidgetGroup();

        spellName = new Label("", Styles.defLabel);
        spellName.setX(0, Align.center);
        spellName.setY(0, Align.center);
        spellName.debug();

        spellPortraitGroup.setFillParent(false);
        spellPortraitGroup.addActor(spellName);

        target.addActor(table(e -> {
            e.update(() -> {
                if(Core.cinput.isJustPressed(Input.Keys.T)) {
                    hideSpell();
                }
                if(Core.cinput.isJustPressed(Input.Keys.G)) {
                    showSpell();
                }
            });
        }));

        target.addActor(spellPortraitGroup);
    }

    public void hideSpell() {
        spellName.addAction(new LabelWriteAction(4, ""));
    }

    public void showSpell() {
        spellName.addAction(new LabelWriteAction(1, "SPELL CARD"));
    }
}
