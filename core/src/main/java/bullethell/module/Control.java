package bullethell.module;

import bullethell.content.Bullets;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Player;
import bullethell.func.Cons;
import bullethell.game.Attack;
import bullethell.game.State;
import bullethell.game.dialog.GameDialog;
import bullethell.game.stage6.nonspells.TestNonSpell;
import bullethell.log.Log;
import bullethell.utils.Time;
import bullethell.utils.Tmp;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import static bullethell.core.Core.*;
import static bullethell.core.Vars.*;

public class Control implements IModule {
    @Override
    public void render() {
        Time.update();

        if(inGame()) {
            if(!paused()) {
                updateGame();
            }
        }
    }

    public void updateGame() {
        enemyBullets.update();
        playerBullets.update();
        player.update();

        if(game.level == null) {
            Log.info("Something went wrong with stage!");
            app.exit();
        }

        game.level.update();

        arena.updateGroup(playerBullets);
    }
    // switch state to gaming
    public void playGame() {
        player.set(100, 100);

        sounds.playMusic(audio.newMusic(files.internal("music/solar_sect_of_mystic_wisdom.mp3")), true);

        Vars.setState(State.inGame);
    }
}
