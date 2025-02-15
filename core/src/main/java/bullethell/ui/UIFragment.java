package bullethell.ui;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.graphics.action.LabelFontScale;
import bullethell.graphics.action.LabelWriteAction;
import bullethell.graphics.g2d.CImage;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.graphics.g2d.SpellScoreDisplay;
import bullethell.module.Styles;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

// todo: show boss health, spell card visuals
public class UIFragment implements Fragment {
    private CWidgetGroup spellPortraitGroup;
    private CImage spellPortrait;

    private Label spellName;
    private Label spellScore;
    private SpellScoreDisplay spellScoreDisplay;
    @Override
    public void build(CWidgetGroup target) {
        spellPortraitGroup = new CWidgetGroup();

        spellName = new Label("", Styles.defLabel);
        spellName.setX(0, Align.center);
        spellName.setY(0, Align.center);

        Rectangle viewport = Vars.arena.viewport;
        float x = viewport.x + viewport.width / 2;
        float y = viewport.y + viewport.height * 3f/4f;

        spellScore = new Label("", Styles.defLabel);
        spellScore.setPosition(x, y);
        spellScore.setAlignment(Align.center | Align.bottom);
        spellScore.setFontScaleY(0.0001f);

        spellScoreDisplay = new SpellScoreDisplay();
        spellScoreDisplay.setX(Core.stage.getWidth() / 2);
        spellScoreDisplay.setY(Core.stage.getHeight() / 2);

        spellPortraitGroup.setFillParent(true);
        spellPortraitGroup.add(spellName, spellScoreDisplay, spellScore);

        target.addActor(spellPortraitGroup);
    }

    public void hideSpell() {
        spellName.addAction(new LabelWriteAction(4, ""));
    }

    public void showSpell() {
        spellName.addAction(new LabelWriteAction(1, "SPELL CARD"));
    }

    public void failedSpell() {
        spellScore.setText("bonus failed...");
        spellScore.addAction(sequence(
            parallel(show(), LabelFontScale.labelFontScale(.4f, 1, 1f)),
            delay(8f,
                sequence(
                    LabelFontScale.labelFontScale(.4f, 1, 0.0001f),
                    hide()
                )
            )
        ));
    }

    public void spellBonus() {
        spellScore.setText("SPELL BONUS: " + Vars.game.spellState.bonus);
        spellScore.addAction(sequence(
            parallel(show(), LabelFontScale.labelFontScale(.4f, 1, 1f)),
            delay(4f,
                sequence(
                    LabelFontScale.labelFontScale(.4f, 1, 0.0001f),
                    hide()
                )
            )
        ));
    }
}
