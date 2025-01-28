package bullethell.module;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.game.State;
import bullethell.graphics.CLabel;
import bullethell.graphics.CStage;
import bullethell.graphics.CWidgetGroup;
import bullethell.ui.MenuFragment;
import bullethell.ui.UIFragment;
import bullethell.ui.dialog.PauseDialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.github.czyzby.kiwi.util.gdx.scene2d.Actors;

public class UI implements IModule {
    public CWidgetGroup menuGroup, uiGroup, gameGroup;

    public MenuFragment menuFragment;
    public UIFragment uiFragment;

    public PauseDialog pauseDialog;

    @Override
    public void create() {
        Fonts.load();
        Styles.load();
        CStage stage = Core.stage;

        stage.table((e) -> {
            e.top().right();
            e.add(new CLabel(() -> "[RED]" + Core.graphics.getFramesPerSecond() + " fps", Styles.kellyLabel24));
        });
        menuGroup = new CWidgetGroup();
        uiGroup = new CWidgetGroup();
        gameGroup = new CWidgetGroup();

        pauseDialog = new PauseDialog();

        menuGroup.visible(Vars::menu);
        uiGroup.visible(Vars::inGame);
        gameGroup.visible(Vars::inGame);

        menuFragment = new MenuFragment();
        menuFragment.build(menuGroup);

        uiFragment = new UIFragment();
        uiFragment.build(uiGroup);

        stage.add(menuGroup, uiGroup, gameGroup);
    }

    @Override
    public void render() {
        Core.stage.act();
        Core.stage.draw();
    }
}
