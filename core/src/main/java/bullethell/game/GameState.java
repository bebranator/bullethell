package bullethell.game;

import bullethell.core.Events;
import bullethell.core.Vars;
import bullethell.entity.type.BossEntity;
import bullethell.func.Cons;
import bullethell.game.spell.SpellCard;
import bullethell.game.spell.SpellCardEndInfo;
import bullethell.game.stage.Stage;
import bullethell.type.BossType;
import bullethell.utils.CPools;

import static bullethell.core.Vars.*;
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

            if(bossSpell.isEnd()) {
                endSpell();
            }
        }else {
            level.update();
        }
//        if(playerSpell != null) playerSpell.update();
    }

    public void bossDeath() {
        // end any current spells, play death animation, shake screen and etc
        // then end the stage since no more waves are expected
        endSpell();
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
    public void endSpell() {
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
    // reset everything and begin new level
    public void setLevel(Stage stage) {
        if(level != null) {
            level.reset();
            level.end();
        }

        bossSpell(null);
        level = stage;

        if(stage != null) stage.begin();
    }

    public BossEntity summonBoss(Cons<BossEntity> cons, BossType type) {
        if(bossEntity != null) return null; // already spawned boss

        BossEntity entity = CPools.obtain(BossEntity.class, BossEntity::new);
        entity.type(type);
        cons.get(entity);
        entity.add();
        return bossEntity = entity;
    }

    // force to end current attack
    public void endAttack() {
        level.endAttack();
    }

    public void bossHealthRanOut() {
        if(bossEntity == null) return; // something went wrong

        endSpell();
    }

    public boolean bossSpell() {
        return bossSpell != null;
    }

    public void clearHazards() {
        enemyBullets.clear();
        playerBullets.clear();
        enemies.clear();
    }
}
