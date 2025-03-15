package bullethell.module;

import bullethell.assets.Assets;
import bullethell.content.PlayerTypes;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.game.GameStats;
import bullethell.game.GameTime;
import bullethell.game.State;
import bullethell.game.dialog.GameDialog;
import bullethell.game.spell.SpellCard;
import bullethell.game.stage.Stage;
import bullethell.game.stage6.Stage6;
import bullethell.game.stagebad.StageBadApple;
import bullethell.ui.dialog.DialogueDialog;
import bullethell.utils.Time;
import com.badlogic.gdx.Input;

import static bullethell.core.Core.*;
import static bullethell.core.Vars.*;

public class Control implements IModule {
    private GameDialog dialog;

    @Override
    public void render() {
        Time.update();

        boolean t = Assets.update();
        if(!t) {
            return;
        }
        if(!Assets.loaded) {
            Assets.postInit();
            Assets.loaded = true;
        }

        if (inGame()) {
            if (!paused()) {
                updateGame();
                GameTime.update();
            }
        }
    }

    public void playDialog(GameDialog dialog) {
        this.dialog = dialog;
    }

    protected void updateGame() {
        // todo: make game dialogs work
        if(game.level == null) {
            Core.panic("Something went wrong with stage!");
        }

        if(cinput.isJustPressed(Input.Keys.ESCAPE)) ui.pauseDialog.show();

        items.update();
        enemyBullets.update();
        playerBullets.update();
        lasers.update();
        enemies.update();
        player.update();

        if(dialog == null) {
            game.levelUpdate();
        }else {
            updateDialog(dialog);
        }

        arena.updateGroup(playerBullets);

        GameStats.update();
    }
    protected void updateDialog(GameDialog dialog) {

    }

    // exit the stage
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

    // clean every entity
    public void reset() {
        playerBullets.clear();
        lasers.clear();
        enemies.clear();
        enemyBullets.clear();
        items.clear();
        player.defaultLocation();
    }

    public void gamePause() {
        Vars.setState(State.pause);
    }
    public void gameResume() {
        Vars.setState(State.inGame);
    }

    Stage stage6 = new StageBadApple();
//    Stage stage6 = new Stage6();
    // switch state to gaming
    public void game() {
        player.type(PlayerTypes.seija);
        ui.menuFragment.setMenu(null);
        GameStats.reset();
        playStage(stage6);

        player.defaultLocation();

        Vars.setState(State.inGame);
        ui.game();
    }
}
