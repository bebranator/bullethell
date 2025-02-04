package bullethell.game.stage6;

import bullethell.game.stage.Stage;
import bullethell.game.stage6.nonspells.SecondNonSpell;
import bullethell.game.stage6.nonspells.TestNonSpell;
import bullethell.graphics.Draw;
import bullethell.module.Fonts;

public class Stage6 extends Stage {
    public Stage6() {
        waves(new TestNonSpell());
    }
}
