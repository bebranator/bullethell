package bullethell.ui.menu;

import bullethell.content.Sounds;
import bullethell.core.Client;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.graphics.action.LabelFontScale;
import bullethell.graphics.g2d.CLabel;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.utils.UpDownEnterListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

// how start game:
// select difficulty
// select player
// (debug) select stage
// gaming
public class MainMenu extends MenuDialog {
    private CWidgetGroup labelGroup;
    private Array<CLabel> labels;

    private int index = 0;

    private Array<Option> options;

    @Override
    protected void shown() {
        showLabels();
    }

    @Override
    protected void hidden() {
        hideLabels();
    }

    @Override
    protected void build() {
        options = new Array<>();
        options.addAll(
            option("начать историю", this::play),
            option("d_выбрать сцену", this::debug_SelectStage),
            option("музыкальная комната", () -> {}),
            option("благодарности", () -> {}),
            option("выйти", this::exit)
        );

        addListener(new UpDownEnterListener(this::up, this::down, this::enter));

        labelGroup = new CWidgetGroup();
        labelGroup.setFillParent(true);
        labels = new Array<>();

        for(Option opt : options) {
            CLabel label = new CLabel();
            label.setOrigin(Align.center | Align.left);
            label.nullSupplier();
            label.setText(opt.name);

            labels.add(label);
            labelGroup.add(label);
        }
        labelGroup.setPosition(0, h);
        labelGroup.setOrigin(Align.center | Align.left);
        updateLabels();

        add(labelGroup);
    }
    void hideLabels() {
        labelGroup.addAction(Actions.moveTo(-400, h, .3f));
    }

    void showLabels() {
        labelGroup.addAction(Actions.moveTo(0, h, .3f));
    }

    final float h = Client.HEIGHT / 2f;
    public void updateLabels() {
        Vars.sounds.option();

        for(int i = 0; i < labels.size; i++) {
            CLabel label = labels.get(i);

            int relative = index - i;
            float tanh = (float) Math.abs(Math.tanh(relative / 2.5f));
            float scl = 1.3f - tanh;
            float y = relative * 50;

            label.addAction(
                parallel(
                    color(relative == 0 ? Color.GOLDENROD : Color.LIGHT_GRAY),
                    moveTo(100 - tanh * 60, y, .1f),
//                    run(() -> label.setFontScale(MathUtils.clamp(scl, .9f, 1.6f))),
                    LabelFontScale.labelFontScale(.1f, MathUtils.clamp(scl, .9f, 1.6f))
//                    rotateTo(tanh * 12 * relative, .1f)
                )
            );
        }
    }

    @Override
    protected Action hideAction() {
        return Actions.sequence(
            Actions.moveTo(-400, 0, .3f),
            Actions.hide()
        );
    }

    void play() {
        Vars.control.game();
    }
    void exit() {
        Core.app.exit();
    }
    void debug_SelectStage() {
        setMenu(Vars.ui.menuFragment.selectDifficulty);
    }
    void up() {
        if(--index < 0) index = Math.max(options.size - 1, 0);
        updateLabels();
    }
    void down() {
        index++;
        index %= Math.max(options.size, 1);
        updateLabels();
    }
    void enter() {
        Vars.sounds.ok();
        options.get(index).run.run();
    }
    public Option option(String name, Runnable run) {
        return new Option(name, run);
    }
    record Option(String name, Runnable run) {}
}
