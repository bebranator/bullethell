package bullethell.ui.dialog;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.func.Cons;
import bullethell.game.State;
import bullethell.module.Styles;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import static bullethell.core.Vars.*;

// todo: pause game when activated, rebuilds
public class CDialog extends Dialog {
    protected Table container = getContentTable();
    public Array<Runnable> shownListeners, hiddenListeners;

    // whether we should pause game when shown or not
    public boolean shouldPause, wasPaused;

    public CDialog(String title, WindowStyle windowStyle) {
        super(title, windowStyle);
        shownListeners = new Array<>();
        hiddenListeners = new Array<>();
        setFillParent(true);
        initTitle();

        hidden(() -> {
            if(shouldPause && !menu()){
                if(!wasPaused){
                    setState(State.inGame);
                }
            }
            ui.menuFragment.blockInputs = false;
        });

        shown(() -> {
            if(shouldPause && !menu()){
                wasPaused = paused();
                setState(State.pause);
            }
            ui.menuFragment.blockInputs = true;
        });
    }

    public void addEscListener(Runnable run) {
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(Input.Keys.ESCAPE == keycode)
                    run.run();
                return false;
            }
        });
    }

    public void initTitle() {
        Label titleLabel = getTitleLabel();
        titleLabel.setStyle(Styles.kellyLabel12);

        titleLabel.setAlignment(Align.center);

        Table title = getTitleTable();
        title.row();
        Image img = new Image(Core.assets.findRegion("pixel"));
        img.setColor(Color.LIGHT_GRAY);
        title.add(img).growX().height(3f).pad(4f);

//        title.image(Core.atlas.drawable("white"), Color.lightGray)
//            .growX().height(3f).pad(4f);
    }

    @Override
    public Dialog show(Stage stage, Action action) {
        shownListeners.forEach(Runnable::run);
        return super.show(stage, action);
    }

    public void show() {
        show(Core.stage);
    }

    @Override
    public void hide(Action action) {
        hiddenListeners.forEach(Runnable::run);
        super.hide(action);
    }

    public void shown(Runnable listener) {
        shownListeners.add(listener);
    }
    public void hidden(Runnable listener) {
        hiddenListeners.add(listener);
    }
}
