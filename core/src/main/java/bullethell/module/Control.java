package bullethell.module;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Player;
import bullethell.func.Cons;
import bullethell.game.State;
import bullethell.game.dialog.GameDialog;
import bullethell.utils.Time;
import bullethell.utils.Tmp;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

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
    Cons<Bullet> updater = (bullet) -> {
        float time = bullet.time();

        bullet.velocity().rotateDeg(0.12f);
        bullet.setSize(time * 0.2f);
//            bullet.color.lerp(1, 0, 1, 1, time / bullet.lifetime());
    };
    void spawn() {

        float px = player.getX();
        float py = player.getY();
//        arena.world.x = px;
//        arena.world.width = px + 1000;
//        arena.world.y = py;
//        arena.viewport.x = px;
//        arena.viewport.width = px + 1000;
//        arena.viewport.y = py;

        final int amount = 1000;
        for(int i = 0; i < amount; i++){
            int finalI = i;
            Bullet.spawn((e) -> {
                Vector2 b = Tmp.v21.set(0, 1).rotateDeg(finalI * 360f / amount);
                e.velocity().set(b);
                e.set(b.x * 70 + px, b.y * 70 + py);
                e.color = Color.RED;
                e.lifetime = 300;
                e.updater = updater;
            });
        }
    }

    // switch state to gaming
    public void playGame() {
        player.set(100, 100);

        Vars.setState(State.inGame);
    }

    public void startDialogue(GameDialog dialogue) {

    }
}
