package bullethell.ui.dialog;

import bullethell.core.Vars;
import bullethell.utils.UpDownEnterListener;
import com.badlogic.gdx.utils.Align;

public class PauseDialog extends CDialog {
    private int index = 0;
    private Option[] options = new Option[] {
        option("continue", this::hide),
        option("exit", this::exit)
    };

    public PauseDialog() {
        super("pause");

        shouldPause = true;
        shown(this::rebuild);
        addListener(new UpDownEnterListener(this::up, this::down, this::enter));
        setFillParent(true);

        table(e -> {
            e.center();
            e.label(() -> options[index].name).align(Align.center);
        });
    }

    void rebuild() {
        index = 0;
    }

    void exit() {
        hide();
        Vars.control.menu();
    }

    public void down() {
        index++;
        index %= Math.max(options.length, 1);
    }
    public void up() {
        if(--index < 0) index = Math.max(options.length - 1, 0);
    }
    void enter() {
        options[index].run.run();
    }
    Option option(String name, Runnable run) {
        return new Option(name, run);
    }
    record Option(String name, Runnable run) {}
}
