package bullethell.ui;

import bullethell.core.Core;
import bullethell.game.GameStats;
import bullethell.graphics.g2d.CLabel;
import bullethell.graphics.g2d.CTable;
import bullethell.graphics.g2d.CWidgetGroup;
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
        });

        displayLabels.setFillParent(true);
        displayLabels.addActor(scoreDisplay);
        displayLabels.setPosition(400, 0, Align.bottom | Align.center);

        target.addActor(displayLabels);
    }
}
