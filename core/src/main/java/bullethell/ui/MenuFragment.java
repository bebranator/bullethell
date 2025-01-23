package bullethell.ui;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.graphics.CButtonsBox;
import bullethell.graphics.CTable;
import bullethell.graphics.CWidgetGroup;
import bullethell.graphics.Shortcuts;
import bullethell.module.Bindings;
import bullethell.module.Tex;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import bullethell.graphics.CButtonsBox.Option;

// todo: menu stuff
public class MenuFragment implements Fragment {
    public CTable selectorImage, buttonsTable;
    public CWidgetGroup inputListener;
    public CButtonsBox buttons;

    public final Array<Option> options = Array.with(
//        new Option("> START STORY <", this::play),
//        new Option("(SELECT STAGE)", this::stageSelect),
//        new Option("SETTINGS", this::settings),
//        new Option("MUSIC ROOM", this::musicRoom),
//        new Option("EXIT", this::exit)
        option(0, "> START STORY <", this::play),
        option(1, "(SELECT STAGE)", this::stageSelect),
        option(2, "SETTINGS", this::settings),
        option(3, "MUSIC ROOM", this::musicRoom),
        option(4, "EXIT", this::exit)
    );

    public Option option(final int num, String name, Runnable run) {
        return new Option(() -> (buttons.index == num ? "[YELLOW]" : "") + name, run);
    }

    public boolean blockInputs = false;

    public void inputs() {
        if(Core.input.isKeyJustPressed(Bindings.down)) {
            buttons.inputDown();
        }
        if(Core.input.isKeyJustPressed(Bindings.up)) {
            buttons.inputUp();
        }
        if(Core.input.isKeyJustPressed(Bindings.accept)) {
            buttons.inputEnter();
        }
    }

    @Override
    public void build(CWidgetGroup target) {
        inputListener = new CWidgetGroup() {
            @Override
            public void act(float delta) {
                // do input processing
                super.act(delta);
                if(blockInputs) return;
                inputs();
            }
        };
        target.addActor(inputListener);

        // lets try cbuttonbox.
        buttons = new CButtonsBox();

        buttons.addOptions(options);
        buttons.rebuild();

        buttons.addToScene();


        buttonsTable = table(e -> {
            e.center();
            e.left();
            e.add(buttons);
        });

        selectorImage = table((e) -> {
            e.left();
            e.image(Tex.black6).width(400).height(40);
        });
        buttonsTable.setZIndex(1);
        selectorImage.setZIndex(0);

        selectorImage.visibility = target.visibility;
        buttons.visibility = target.visibility;
    }

    void play() {
        // todo: play state
//        Shortcuts.infoText(() -> "no permissions");
        Vars.control.playGame();
    }
    void exit() {
        Core.app.exit();
    }
    void stageSelect() {

    }
    void settings() {

    }
    void musicRoom() {
        // todo: music room dialog
        Shortcuts.infoText(() -> "no permissions");
    }
}
