package bullethell.game;

import bullethell.core.Events;
import bullethell.core.Vars;
import bullethell.entity.type.BossEntity;
import bullethell.func.Cons;
import bullethell.game.spell.SpellCard;
import bullethell.game.spell.SpellCardEndInfo;
import bullethell.game.stage.BossWaves;
import bullethell.game.stage.Stage;
import bullethell.game.stage6.Stage6;
import bullethell.log.Log;
import bullethell.type.BossType;
import bullethell.utils.CPools;
import bullethell.utils.E;

// current attack
public class GameState {
    public Stage level;
    public BossEntity bossEntity;
    // what spell we in
    public SpellCard bossSpell = null, playerSpell = null;
    public SpellState spellState = new SpellState();

    public Difficulty difficulty = Difficulty.lunatic;

    public boolean showBossDisplay; // show boss health and remaining spells

    public GameState() {
        Events.on(Ev.PlayerDeathEvent.class, (e) -> {
            if(bossSpell == null) return;

            if(!bossSpell.hasTags(SpellCard.SC_NO_DEATH_BONUS_LOSS)) {
                spellState.bonus = 0;
                spellState.died = true;
            }
        });
    }

    public void levelUpdate() {
        if(bossSpell != null) {
            bossSpell.superUpdate();

            // burn spell bonus
            if(!bossSpell.hasTags(SpellCard.SC_NO_BONUS_BURN)) {
                // minimal spell bonus is 1500000
                spellState.bonus = Math.max(spellState.bonus - 200, spellState.died ? 0 : 1500000);
            }

            if(bossSpell.isEnd()) {
                bossSpell.end();
                SpellCardEndInfo info = SpellCardEndInfo.get();

                info.died = spellState.died;
                info.spellTimeLeft = bossSpell.lifetime - bossSpell.time;
                info.spellTimeout = info.spellTimeLeft == 0;
                info.spell = bossSpell;
                info.spellBonus = spellState.bonus;

                Events.fire(Ev.SpellCardEndEvent.class, Ev.SpellCardEndEvent.get(info, bossSpell));
                bossSpellEnd(info);

                bossSpell(null);
            }
        }else {
            level.update();
        }
//        if(playerSpell != null) playerSpell.update();
    }

    public void levelDraw() {
        level.draw();
    }

    void bossSpellEnd(SpellCardEndInfo info) {
        // no bonus for you!
        if(bossSpell.hasTags(SpellCard.SC_NO_TIMEOUT_BONUS) && info.spellTimeout) {
            Vars.ui.failedBonus();
            return;
        }
        Vars.ui.spellBonus(spellState.bonus);
        GameStats.depositScore(spellState.bonus);
    }

    // null if no spell card now
    public void bossSpell(SpellCard bossSpell) {
        if(bossSpell == null) {
            Vars.ui.hideSpellUi();
            this.bossSpell = null;

            return;
        }
        // show spell, set new spell card value
        this.bossSpell = bossSpell;
        spellState.bonus = (int) (bossSpell.spellBonus * difficulty.bonusModifier);
        Vars.ui.showSpellUi();
    }

    // todo: do player spell
    public void playerSpell(SpellCard playerSpell) {
        this.playerSpell = playerSpell;
    }
    // reset everything and begin new level
    public void setLevel(Stage stage) {
        if(level != null) level.reset();

        bossSpell(null);
        level = stage;
    }

    public BossEntity summonBoss(Cons<BossEntity> cons, BossType type) {
        BossEntity entity = CPools.obtain(BossEntity.class, BossEntity::new);
        entity.type(type);
        cons.get(entity);
        entity.add();

        return entity;
    }
//
//    public void spell(SpellCard card) {
//        //todo: nullify bonus and spell state, reset timer
//        endSpell();
//        spellState.reset();
//        this.spell = card;
//    }
//
//    // todo: deposit spell bonus
//    public void endSpell() {
//        // hide spell ui
//        if(spell.hasTags(SpellCard.SC_NO_TIMEOUT_BONUS))
//
//        GameStats.depositScore(spellState.bonus);
//
//        Vars.ui.hideSpellUi();
//    }
//
//    public void beginSpell() {
//        // show spell display
//        // portrait and etc
//        Vars.ui.spell(bossEntity.type());
//    }
//
//    // sets current boss (summons entity, makes display and etc)
//    public void boss(Cons<BossEntity> cons) {
//        bossEntity = BossEntity.spawn(cons);
//
//        Vars.ui.showBossDisplay();
//    }
//
//    public void showDisplay(boolean value) {
//        this.showBossDisplay = value;
//
//        if(showBossDisplay) Vars.ui.showBossDisplay();
//        else Vars.ui.hideBossDisplay();

//    }
}
