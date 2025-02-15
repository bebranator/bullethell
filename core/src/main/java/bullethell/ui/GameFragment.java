package bullethell.ui;

import bullethell.core.Core;
import bullethell.core.Events;
import bullethell.core.Vars;
import bullethell.game.Ev;
import bullethell.game.GameStats;
import bullethell.graphics.g2d.*;
import bullethell.module.Styles;
import bullethell.utils.Time;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

// todo: score count, lives, power
//  boss display
public class GameFragment implements Fragment {
    public CWidgetGroup displayLabels = new CWidgetGroup();
    public CWidgetGroup bossDisplay = new CWidgetGroup();
    public CTable tbl;

    @Override
    public void build(CWidgetGroup target) {
        TimerDisplay timer = new TimerDisplay();
        CLabel deaths = new CLabel(() -> GameStats.deaths + " смертей");
        timer.displayTime(true);
        Events.on(Ev.SpellCardEndEvent.class, e -> {
            timer.displayTime(false);
        });

        timer.timeUpdater(() -> {
            if(Vars.game.bossSpell == null) return 0;
            return (Vars.game.bossSpell.lifetime - Vars.game.bossSpell.time) / 60f;
        });

        ScoreDisplay score = new ScoreDisplay();
        score.setAlignment(Align.left | Align.bottom);

        displayLabels.setFillParent(true);
        displayLabels.addActor(score);
        displayLabels.addActor(timer);
        displayLabels.addActor(deaths);
        displayLabels.setPosition(400, 400);

        tbl = table(e -> {
            e.center().right();
            deaths.setOrigin(Align.center | Align.right);
            e.add(deaths);
        }).fillParent(true);

        target.add(displayLabels, tbl);
    }
}
