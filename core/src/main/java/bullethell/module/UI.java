package bullethell.module;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.graphics.g2d.CLabel;
import bullethell.graphics.g2d.CStage;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.ui.GameFragment;
import bullethell.ui.MenuFragment;
import bullethell.ui.UIFragment;
import bullethell.ui.dialog.DialogueDialog;
import bullethell.ui.dialog.PauseDialog;

public class UI implements IModule {
    public CWidgetGroup menuGroup, uiGroup, gameGroup;

    public MenuFragment menuFragment;
    public UIFragment uiFragment;
    public GameFragment gameFragment;

    public PauseDialog pauseDialog;
    public DialogueDialog dialogueDialog;

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
        menuGroup.setFillParent(true);
        uiGroup.setFillParent(true);
        gameGroup.setFillParent(true);

        pauseDialog = new PauseDialog();
        dialogueDialog = new DialogueDialog();

        menuGroup.visible(Vars::menu);
        uiGroup.visible(Vars::inGame);
        gameGroup.visible(Vars::inGame);

        menuFragment = new MenuFragment();
        menuFragment.build(menuGroup);

        uiFragment = new UIFragment();
        uiFragment.build(uiGroup);

        gameFragment = new GameFragment();
        gameFragment.build(gameGroup);

        stage.add(menuGroup, uiGroup, gameGroup);
    }

    public void menu() {
        Core.stage.setKeyboardFocus(menuGroup);
    }
    public void game() {
        Core.stage.setKeyboardFocus(null);
    }

    public void spell() {
        // summon spell card display in ui group
    }

    @Override
    public void render() {
        Core.stage.act();
        Core.stage.draw();
    }
}
