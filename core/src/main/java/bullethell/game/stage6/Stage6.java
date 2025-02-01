package bullethell.game.stage6;

import bullethell.game.stage.Stage;
import bullethell.game.stage6.nonspells.SecondNonSpell;
import bullethell.game.stage6.nonspells.TestNonSpell;
import bullethell.graphics.Draw;
import bullethell.module.Fonts;

public class Stage6 extends Stage {
    public Stage6() {
        waves(new TestNonSpell(), new SecondNonSpell());
    }

    @Override
    public void draw() {
        Draw.textMode();
        Draw.text(Fonts.kelly12, waves.size + "", 300, 320);
        Draw.text(Fonts.kelly12, waves.get(0) + "", 300, 300);
        Draw.textEnd();
    }
}
