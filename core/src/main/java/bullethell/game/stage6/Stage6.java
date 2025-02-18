package bullethell.game.stage6;

import bullethell.content.Bosses;
import bullethell.game.stage.BossWaves;
import bullethell.game.stage.Stage;
import bullethell.game.stage6.nonspells.TestNonSpell;
import bullethell.game.stage6.spells.BulletCircle;
import bullethell.game.stage6.spells.PetaFlare;

public class Stage6 extends Stage {
    public Stage6() {
        waves(
            new BossWaves(Bosses.okuu, new PetaFlare()
        ));
    }
}
