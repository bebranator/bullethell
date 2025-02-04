package bullethell.game;

import bullethell.entity.type.BossEntity;
import bullethell.func.Cons;
import bullethell.game.spell.SpellCard;
import bullethell.game.stage.Stage;
import bullethell.game.stage6.Stage6;

// current attack
public class GameState {
    public Stage level;
    public boolean showBossDisplay; // show boss health and remaining spells
    public BossEntity bossEntity;
    // what spell we in
    public SpellCard spell;

    // reset everything and begin new level
    public void setLevel(Stage stage) {
        if(level != null) level.reset();
        level = stage;
    }

    public void spell(SpellCard card) {
        //todo: nullify bonus and spell state, reset timer
        this.spell = card;
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
