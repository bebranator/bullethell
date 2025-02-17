package bullethell.ui.dialog;

import bullethell.core.Vars;
import bullethell.game.GameState;
import bullethell.game.GameStats;
import bullethell.module.Styles;
import bullethell.utils.EscapeListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class StageResultsDialog extends CDialog {
    private TypingLabel score, deaths;

    public StageResultsDialog() {
        super("");
        shouldPause = true;
        hideTitle();
        setFillParent(true);
        shown(this::rebuild);
        hidden(() -> Vars.control.menu());
        addListener(new EscapeListener(this::hide));
        score = new TypingLabel("", Styles.kellyLabel24);
        score.setAlignment(Align.center, Align.bottom);

        deaths = new TypingLabel("", Styles.kellyLabel24);
        deaths.setAlignment(Align.center, Align.bottom);
        container.center();
        container.add(score);
        container.row();
        container.add(deaths);
    }

    void rebuild() {
        score.setVariable("score", String.valueOf(GameStats.score));
        score.restart("{FAST}Результат >> {NORMAL}{VAR=score}");

        deaths.setVariable("deaths", String.valueOf(GameStats.deaths));
        deaths.restart("{FAST}Смертей >> {NORMAL}{VAR=deaths}");
    }
}
