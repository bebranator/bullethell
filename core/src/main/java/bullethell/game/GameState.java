package bullethell.game;

import bullethell.entity.type.BossEntity;
import bullethell.func.Cons;
import bullethell.game.stage.Stage;
import bullethell.game.stage6.Stage6;

// current attack
public class GameState {
    public Stage level = new Stage6();
    public boolean showBossDisplay; // show boss health and remaining spells
    public BossEntity bossEntity;

    // reset everything and begin new level
    public void setLevel(Stage stage) {
        level.reset();
        level = stage;
    }

    // sets current boss (summons entity, makes display and etc)
    public void boss(Cons<BossEntity> cons) {
        bossEntity = BossEntity.spawn(cons);

        showBossDisplay = true;
    }

    public void updateBoss() {

    }

    public void showDisplay(boolean value) {
        this.showBossDisplay = value;
    }

    public void levelUpdate() {
        level.update();
    }
    public void levelDraw() {
        level.draw();
    }
}
