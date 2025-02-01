package bullethell.module;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.graphics.g2d.CLabel;
import bullethell.graphics.g2d.CStage;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.ui.MenuFragment;
import bullethell.ui.UIFragment;
import bullethell.ui.dialog.DialogueDialog;
import bullethell.ui.dialog.PauseDialog;

public class UI implements IModule {
    public CWidgetGroup menuGroup, uiGroup, gameGroup;

    public MenuFragment menuFragment;
    public UIFragment uiFragment;

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

        pauseDialog = new PauseDialog();
        dialogueDialog = new DialogueDialog();

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
