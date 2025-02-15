package bullethell.ui.menu;

import bullethell.content.Heroes;
import bullethell.core.Client;
import bullethell.core.Vars;
import bullethell.module.Styles;
import bullethell.type.Hero;
import bullethell.utils.UpDownEnterListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class SelectPlayer extends MenuDialog {
    private Array<Team> teams;

    public Team current;
    public Image base, focus;
    public Label label;

    private int index;

    @Override
    protected void build() {
        teams = new Array<>();
        teams.addAll(
            new Team("физматы (вроде)", Heroes.bazarov, Heroes.zheka),
            new Team("bob team", Heroes.bob, Heroes.evilBob)
        );
        index = 0;
        gray = new Color(.5f, .5f, .5f, .4f);

        label = new Label("team", Styles.defLabel);
        label.setAlignment(Align.center, Align.center);

        base = new Image();
        focus = new Image();

        base.setSize(200, 320);
        focus.setSize(200 * 3/4f, 320 * 3/4f);

        base.setPosition(Client.WIDTH / 4f, Client.HEIGHT / 2f);
        focus.setPosition(Client.WIDTH / 4f - 80, Client.HEIGHT / 2f + 80f);
        label.setPosition(Client.WIDTH / 4f, Client.HEIGHT / 2f - 80);
        base.setAlign(Align.center);
        focus.setAlign(Align.center);

        update();
        // a lot of boiler plate fuck
        escapeListener(Vars.ui.menuFragment.selectDifficulty);
        addListener(new UpDownEnterListener(this::up, this::down, this::enter));
        add(base, focus, label);
        base.toFront();
    }

    Color gray;
    void update() {
        current = teams.get(index);

        focus.setDrawable(current.base().portraitDrawable());
        focus.setColor(gray);
        base.setDrawable(current.base().portraitDrawable());
        base.toFront();
        label.setText(current.name());
    }

    void up() {
        if(--index < 0) index = Math.max(teams.size - 1, 0);
        update();
    }
    void down() {
        index++;
        index %= Math.max(teams.size, 1);
        update();
    }
    void enter() {
        Vars.sounds.ok();
//        setMenu();
    }
}

