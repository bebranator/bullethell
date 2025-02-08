package bullethell.graphics.g2d;

import bullethell.core.Vars;
import bullethell.module.Styles;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.text.DecimalFormat;

public class SpellScoreDisplay extends Label {
    private final DecimalFormat format = new DecimalFormat("000000000");

    public SpellScoreDisplay() {
        super("000000000", Styles.defLabel);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setText(format.format(Vars.game.spellState.bonus));
    }
}
