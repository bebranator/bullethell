package bullethell.module;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.game.GameStats;
import bullethell.game.GameTime;
import bullethell.game.State;
import bullethell.game.spell.SpellCard;
import bullethell.game.stage.Stage;
import bullethell.game.stage6.Stage6;
import bullethell.game.stagebad.StageBadApple;
import bullethell.utils.Time;
import com.badlogic.gdx.Input;

import static bullethell.core.Core.*;
import static bullethell.core.Vars.*;

public class Control implements IModule {
    @Override
    public void render() {
        Time.update();

        if(inGame()) {
            if(!paused()) {
                updateGame();
                GameTime.update();
            }
        }
    }

    public void updateGame() {
        if(game.level == null) {
            Core.panic("Something went wrong with stage!");
        }

        if(cinput.isJustPressed(Input.Keys.ESCAPE)) ui.pauseDialog.show();

        enemyBullets.update();
        playerBullets.update();
        lasers.update();
        enemies.update();
        player.update();

        game.levelUpdate();

        arena.updateGroup(playerBullets);
        GameStats.update();
    }
    // exit the stage
    // todo: show loading screen
    public void menu() {
        sounds.stopMusic();

        player.defaultLocation();
        Vars.setState(State.menu);

        game.setLevel(null);
        game.clearHazards();
        ui.menu();
        GameTime.reset();
    }

    public void playStage(Stage stage) {
        reset();
        game.setLevel(stage);
    }

    public void card(SpellCard card) {
//        game.spell(card);
    }

    // clean every entity
    public void reset() {
        playerBullets.clear();
        lasers.clear();
        enemies.clear();
        enemyBullets.clear();
        player.defaultLocation();
    }

    public void gamePause() {
        Vars.setState(State.pause);
    }
    public void gameResume() {
        Vars.setState(State.inGame);
    }

    Stage stage6 = new StageBadApple();
    // switch state to gaming
    public void game() {
        ui.menuFragment.setMenu(null);
        GameStats.reset();
        playStage(stage6);
        player.defaultLocation();

        Vars.setState(State.inGame);
        ui.game();
    }
}
