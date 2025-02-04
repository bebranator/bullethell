package bullethell.ui;

import bullethell.core.Core;
import bullethell.game.GameStats;
import bullethell.graphics.g2d.BossBarDisplay;
import bullethell.graphics.g2d.CLabel;
import bullethell.graphics.g2d.CTable;
import bullethell.graphics.g2d.CWidgetGroup;
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
            e.image(Core.atlas.findRegion("poweritem")).pad(8).size(32f);
            e.label(() -> "POWER " + GameStats.power);
            BossBarDisplay display = new BossBarDisplay();
            display.setBounds(0, 0, 400, 30);
            display.setColor(Color.FIREBRICK);
            display.setProgress(.6f);
            e.row();
            e.add(display);

            e.update(() -> {
                if(Core.cinput.isJustPressed(Input.Keys.E)) {
                    display.setProgress(1f);
                }
                if(Core.cinput.isJustPressed(Input.Keys.O)) {
                    display.setProgress(0f);
                }
            });
        });

        displayLabels.setFillParent(true);
        displayLabels.addActor(scoreDisplay);
        displayLabels.setPosition(400, 0, Align.bottom | Align.center);

        target.addActor(displayLabels);
    }
}
