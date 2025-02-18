package bullethell.game.stage;

import bullethell.core.Core;
import bullethell.game.Attack;
import bullethell.graphics.Shortcuts;
import bullethell.log.Log;

public class BossWaveBaseWait extends Attack {
    private final String stageName;

    public BossWaveBaseWait(String stageName) {
        lifetime = 5 * 60;
        this.stageName = stageName;
    }
    @Override
    public void begin() {
        Log.info("BEGIN");
        Shortcuts.stage(stageName);
    }
}
