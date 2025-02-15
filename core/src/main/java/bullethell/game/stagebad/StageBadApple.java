package bullethell.game.stagebad;

import bullethell.game.stage.Stage;

public class StageBadApple extends Stage {
    public StageBadApple() {
        waves(
            new BadAppleWave()
        );
    }
}
