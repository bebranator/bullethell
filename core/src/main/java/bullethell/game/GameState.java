package bullethell.game;

import bullethell.core.Vars;
import bullethell.entity.type.BossEntity;
import bullethell.func.Cons;
import bullethell.game.spell.SpellCard;
import bullethell.game.stage.Stage;
import bullethell.game.stage6.Stage6;

// current attack
public class GameState {
    public Stage level;
    public BossEntity bossEntity;
    // what spell we in
    public SpellCard spell;
    public SpellState spellState = new SpellState();

    public Difficulty difficulty;

    public boolean showBossDisplay; // show boss health and remaining spells

    // reset everything and begin new level
    public void setLevel(Stage stage) {
        if(level != null) level.reset();
        spellState.reset();
        level = stage;
    }

    public void spell(SpellCard card) {
        //todo: nullify bonus and spell state, reset timer
        endSpell();
        spellState.reset();
        this.spell = card;
    }

    // todo: deposit spell bonus
    public void endSpell() {
        // hide spell ui
        if(spell.hasTags(SpellCard.SC_NO_TIMEOUT_BONUS))

        GameStats.depositScore(spellState.bonus);

        Vars.ui.hideSpellUi();
    }

    public void beginSpell() {
        // show spell display
        // portrait and etc
        Vars.ui.spell(bossEntity.type());
    }

    // sets current boss (summons entity, makes display and etc)
    public void boss(Cons<BossEntity> cons) {
        bossEntity = BossEntity.spawn(cons);

        Vars.ui.showBossDisplay();
    }

    public void showDisplay(boolean value) {
        this.showBossDisplay = value;

        if(showBossDisplay) Vars.ui.showBossDisplay();
        else Vars.ui.hideBossDisplay();
    }

    public void levelUpdate() {
        level.update();

        if(spell != null) {
            spell.superUpdate();

            if (spell.isEnd()) {
                spell.end();
                spell(null);
            }
        }
    }

    public void levelDraw() {
        level.draw();
    }
}
