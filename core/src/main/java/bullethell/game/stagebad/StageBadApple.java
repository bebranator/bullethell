package bullethell.game.stagebad;

import bullethell.content.Bosses;
import bullethell.game.stage.BossWaveBaseWait;
import bullethell.game.stage.BossWaves;
import bullethell.game.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class StageBadApple extends Stage {
    public StageBadApple() {
        try {
            waves(
                new BossWaveBaseWait("Ёкстра уровень. \n BAD APPLE!!!"),
                new BadAppleWave()
            );
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
