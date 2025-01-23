package bullethell.graphics;

import bullethell.core.Core;
import bullethell.func.Cons;
import bullethell.func.Cons1;
import bullethell.func.Supp;
import bullethell.module.Bindings;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class CButtonsBox extends CTable {
    private Array<Option> options;
    private Cons1<CTable, Option> optionBuilder = (container, option) -> {
        container.label(option.name);
        container.row();
    };
    public int index = 0;
    private int optionSize = 0;

    public CButtonsBox(boolean addListener) {
        options = new Array<>();
        rebuild();

        if(addListener) this.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                Core.app.exit();

                if(keycode == Bindings.down) inputDown();
                if(keycode == Bindings.up) inputUp();
                if(keycode == Bindings.accept) inputEnter();

                return false;
            }
        });
    }

    public CButtonsBox() {
        this(false);
    }
    public void addOptions(Option... options) {
        this.options.addAll(options);
    }
    public void addOptions(Array<Option> options) {
        this.options.addAll(options);
    }

    public void optionBuilder(Cons1<CTable, Option> option) {
        this.optionBuilder = option;
    }

    public void inputEnter() {
        if(options.isEmpty()) return;

        current().triggered.run();
    }

    public void inputDown() {
        index++;
        index %= Math.max(optionSize, 1);
    }
    public void inputUp() {
        if(--index < 0) index = Math.max(optionSize - 1, 0);
    }

    // rebuild option buttons
    public void rebuild() {
        optionSize = options.size;
        // nothing to build lmao
        if(options.isEmpty()) return;

        clear();
        for (Option opt : options) {
            optionBuilder.get(this, opt);
        }
    }

    public Option current() {
        return options.get(index);
    }

    public record Option(Supp<String> name, Runnable triggered) {
        public Option(String name, Runnable trigger) {
            this(() -> name, trigger);
        }
    }
}
