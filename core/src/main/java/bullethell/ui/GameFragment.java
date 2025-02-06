package bullethell.ui;

import bullethell.core.Core;
import bullethell.game.GameStats;
import bullethell.graphics.g2d.*;
import bullethell.utils.Time;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Align;

// todo: score count, lives, power
//  boss display
public class GameFragment implements Fragment {
    public CWidgetGroup displayLabels = new CWidgetGroup();
    public CWidgetGroup bossDisplay = new CWidgetGroup();

    @Override
    public void build(CWidgetGroup target) {
        CTable scoreDisplay = table(e -> {
            e.center();
            e.image(Core.atlas.findRegion("poweritem")).size(32f).left();
            e.label(() -> "POWER " + GameStats.power).left();
            BossBarDisplay display = new BossBarDisplay();
            display.setOrigin(Align.center | Align.left);
            display.setBounds(0, 0, 200, 30);
            display.setColor(Color.FIREBRICK);
            display.setProgress(.6f);
            e.row();
            e.add(display);
            e.row();
            TimerDisplay timer = new TimerDisplay();
            timer.displayTime(true);

            e.update(() -> {
                if(Core.cinput.isJustPressed(Input.Keys.E)) {
                    display.setProgress(1f);
                }
                if(Core.cinput.isJustPressed(Input.Keys.O)) {
                    display.setProgress(0.5f);
                }
                timer.setTime(Time.time * 30);
            });
            e.add(timer);
        });

        displayLabels.setFillParent(true);
        displayLabels.addActor(scoreDisplay);
        displayLabels.setPosition(400, 0, Align.bottom | Align.center);

        target.addActor(displayLabels);
    }
}
