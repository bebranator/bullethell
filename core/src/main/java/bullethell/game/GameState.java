package bullethell.game;

import bullethell.core.Core;
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
import com.badlogic.gdx.utils.Array;

// current attack
public class GameState {
    public Stage level;
    public BossEntity bossEntity;
    // what spell we in
    public SpellCard bossSpell = null, playerSpell = null;
    public SpellState spellState = new SpellState();

    public Difficulty difficulty = Difficulty.lunatic;

    public GameState() {
        Events.on(Ev.PlayerDeathEvent.class, (e) -> {
            if(bossSpell == null) return;
//            Core.app.exit();

            spellState.died = true;
            if(!bossSpell.hasTags(SpellCard.SC_NO_DEATH_BONUS_LOSS)) {
                spellState.bonus = 0;
            }
        });
    }

    public void levelUpdate() {
        if(bossSpell != null) {
            bossSpell.superUpdate();
            int bonusBurn = bossSpell.hasTags(SpellCard.SC_NO_BONUS_BURN) ? 0 :
                bossSpell.byDifficulty(4, 6, 6, 8, 8) * 50;

            spellState.bonus -= bonusBurn;

            // burn spell bonus
//            if(!bossSpell.hasTags(SpellCard.SC_NO_BONUS_BURN)) {
//                // minimal spell bonus is 1500000
//                spellState.bonus = Math.max(spellState.bonus - 200,
//                spellState.died && !bossSpell.hasTags(SpellCard.SC_NO_DEATH_BONUS_LOSS) ? 0 : 1500000);
//            }

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

    public void setBoss(BossWaves waves) {

    }

    public void bossDeath() {
        // end current spell
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

    public void levelDraw() {
        level.draw();
    }

    void bossSpellEnd(SpellCardEndInfo info) {
        // no bonus for you!
        if(bossSpell.hasTags(SpellCard.SC_NO_TIMEOUT_BONUS) && info.spellTimeout) {
            Vars.ui.spellBonus(0);
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
    public boolean bossSpell() {
        return bossSpell != null;
    }
}
