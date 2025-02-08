package bullethell.ui;

import bullethell.core.Client;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.graphics.Draw;
import bullethell.graphics.action.LabelFontScale;
import bullethell.graphics.g2d.CButtonsBox;
import bullethell.graphics.g2d.CLabel;
import bullethell.graphics.g2d.CTable;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.graphics.Shortcuts;
import bullethell.module.Bindings;
import bullethell.module.Tex;
import bullethell.utils.UpDownEnterListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import bullethell.graphics.g2d.CButtonsBox.Option;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

// todo: menu stuff
public class MenuFragment implements Fragment {
    public final Array<Option> options = Array.with(
        option("> START STORY <", this::play),
        option("(SELECT STAGE)", this::stageSelect),
        option("SETTINGS", this::settings),
        option("MUSIC ROOM", this::musicRoom),
        option("EXIT", this::exit)
    );
    private Array<CLabel> labels = new Array<>();

    private CWidgetGroup labelGroup = new CWidgetGroup();

    private int optionIndex = 0;
    private int index = 0;

    private boolean hiddenLabels = false;

    public Option option(String name, Runnable run) {
        optionIndex++;
        return new Option(optionIndex, name, run);
    }

    final float h = Client.HEIGHT / 2f;
    public void updateLabels() {
        for(int i = 0; i < labels.size; i++) {
            CLabel label = labels.get(i);
            label.setOrigin(Align.left | Align.center);

            int relative = index - i;
            float tanh = (float) Math.abs(Math.tanh(relative / 2.5f));
            float scl = 1.3f - tanh;
            float y = h - relative * -40;

            label.addAction(
                parallel(
                    color(relative == 0 ? Color.GOLDENROD : Color.LIGHT_GRAY),
                    moveTo(100 - tanh * 50, y, .1f),
//                    run(() -> label.setFontScale(MathUtils.clamp(scl, .9f, 1.6f))),
                    LabelFontScale.labelFontScale(.1f, MathUtils.clamp(scl, .9f, 1.6f))
                )
            );
        }
    }

    public void down() {
        if(hiddenLabels) return;

        index++;
        index %= Math.max(options.size, 1);
        updateLabels();
    }
    public void up() {
        if(hiddenLabels) return;

        if(--index < 0) index = Math.max(options.size - 1, 0);
        // do bunch o math and add correct actions
        updateLabels();
    }
    void accept() {
        if(hiddenLabels) return;

        options.get(index).triggered().run();
    }

    @Override
    public void build(CWidgetGroup target) {
        target.addListener(new UpDownEnterListener(this::up, this::down, this::accept));

        for(Option o : options) {
            CLabel label = new CLabel(o.name());

//            label.setDebug(true);
            labels.add(label);
            labelGroup.addActor(label);
//            target.addActor(label);
        }
//        labelGroup.debugAll();
        target.addActor(labelGroup);
        updateLabels();
        in();
        showLabels();

        Vars.ui.menu();
    }
    void in() {
        labelGroup.addAction(moveTo(-400, 0));
    }

    public void hideLabels() {
        labelGroup.addAction(moveTo(-400, 0, .6f));
        hiddenLabels = true;
    }
    public void showLabels() {
        labelGroup.addAction(moveTo(0, 0, .6f));
        hiddenLabels = false;
    }

    void play() {
        hideLabels();
//        Shortcuts.infoText(() -> "no permissions");
        Vars.control.game();
    }
    void exit() {
        Core.app.exit();
    }
    void stageSelect() {
        if(hiddenLabels) showLabels();
        else hideLabels();
    }
    void settings() {
    }
    void musicRoom() {
        // todo: music room dialog
        Shortcuts.infoText(() -> "no permissions");
    }
}
