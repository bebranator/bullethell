package bullethell.graphics.g2d;

import bullethell.game.GameStats;
import bullethell.module.Styles;
import bullethell.utils.Interval;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ScoreDisplay extends Label {
    private final DecimalFormat format = new DecimalFormat("000000000");

    public ScoreDisplay() {
        super("000000000", Styles.defLabel);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setText(format.format(GameStats.displayScore));
    }
}
