package bullethell.ui.menu;

import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.log.Log;
import bullethell.utils.EscapeListener;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public abstract class MenuDialog extends CWidgetGroup {
    public MenuDialog() {
//        setFillParent(true);
        build();
        Vars.ui.menuGroup.add(this);
        setSize(Core.stage.getWidth(), Core.stage.getHeight());
        setVisible(false);
        setPosition(getWidth(), 0);
    }
    public void escapeListener() {
        addListener(new EscapeListener(this::toPreviousMenu));
    }
    public void escapeListener(MenuDialog dialog) {
        addListener(new EscapeListener(() -> Vars.ui.menuFragment.setMenu(dialog)));
    }

    // when we want menu dialog appear
    public void show() {
        shown();
        addAction(showAction());
    }
    public void hide() {
        hidden();
        addAction(hideAction());
    }

    protected void build() {}

    protected void shown() {}

    protected void hidden() {}

    protected Action showAction() {
        return Actions.parallel(
            Actions.moveTo(0, 0, .3f),
            Actions.show()
        );
    }
    protected Action hideAction() {
        return Actions.sequence(
            Actions.moveTo(getWidth(), 0, .3f),
            Actions.hide()
        );
    }

    // switches to previous menu
    public void toPreviousMenu() {
        Vars.ui.menuFragment.previousMenu();
    }
    public void setMenu(MenuDialog dialog) {
        Vars.ui.menuFragment.setMenu(dialog);
    }
}
