package bullethell.ui.menu;

import bullethell.core.Client;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.game.Difficulty;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.module.Styles;
import bullethell.utils.UpDownEnterListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SelectDifficulty extends MenuDialog {
    final float h = Client.HEIGHT / 2f;

    public static final Difficulty[] difficultyArray = Difficulty.values();

    private TypingLabel description;
    private Label title;
    private CWidgetGroup diffGroup;
    private Array<Image> difficulties;
    private Difficulty current;
    private int index;

    @Override
    protected void build() {
        // a lot of boiler plate fuck

        index = 1;
        current = Difficulty.normal;
        diffGroup = new CWidgetGroup();
        diffGroup.setFillParent(true);
        difficulties = new Array<>();

        description = new TypingLabel("NO DESCRIPTION", Styles.defLabel);
        description.setSize(200, 80);
        description.setPosition(Client.WIDTH / 2f, Client.HEIGHT / 2f);
        description.setWrap(true);
        description.setAlignment(Align.center, Align.center);

        for(Difficulty diff : difficultyArray) {
            Image image = new Image(Core.atlas.findRegion(diff.name));

            image.setAlign(Align.center | Align.right);
            image.setOrigin(Align.center | Align.right);
            difficulties.add(image);
            diffGroup.add(image);
        }

        diffGroup.setPosition(Client.WIDTH - 240, h);
        diffGroup.setOrigin(Align.center | Align.right);
        add(diffGroup, description);

        escapeListener(Vars.ui.menuFragment.mainMenu);
        addListener(new UpDownEnterListener(this::up, this::down, this::enter));
    }

    @Override
    protected void hidden() {
        index = 1;
        update();
    }

    @Override
    protected void shown() {
        update();
    }

    void update() {
        Vars.sounds.option();
        current = difficultyArray[index];
        for (int i = 0; i < difficulties.size; i++) {
            Image img = difficulties.get(i);

            int relative = index - i;
            float tanh = (float) Math.abs(Math.tanh(relative / 3f));

            float x = tanh * 50;
            float y = relative * 40;

            img.addAction(
                parallel(
                    moveTo(x, y, .1f)
                )
            );
        }
        description.restart(current.difficultyDescription());
    }
    void up() {
        if(--index < 0) index = Math.max(difficultyArray.length - 1, 0);
        update();
    }
    void down() {
        index++;
        index %= Math.max(difficultyArray.length, 1);
        update();
    }
    void enter() {
        Vars.sounds.ok();
        Vars.game.difficulty = current;
        setMenu(Vars.ui.menuFragment.selectPlayer);
//        setMenu();
    }
}
