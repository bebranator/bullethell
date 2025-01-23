package bullethell.module;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Player;
import bullethell.game.State;
import bullethell.utils.Time;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

import static bullethell.core.Vars.*;

public class Control implements IModule {
    @Override
    public void render() {
        Input input = Core.input;
        Time.update();

        if(inGame()) {
            if(!paused()) {
                enemyBullets.update();
                player.update();
                if(input.isKeyJustPressed(Input.Keys.ESCAPE)) ui.pauseDialog.show();

                if(input.isKeyJustPressed(Input.Keys.I)) spawn();
            }
        }
    }
    void spawn() {
        Bullet bullet = Bullet.spawn((e) -> {
            e.lifetime = 60 * 10;
            e.setSize(12);
            e.color = Color.BLUE;
            e.velocity().set(1, 0);

            float x = player.getX() + 40;
            float y = player.getY() + 40;
            e.set(x, y);
        });
    }

    // switch state to gaming
    public void playGame() {
        player.set(100, 100);

        Vars.setState(State.inGame);
    }
}
