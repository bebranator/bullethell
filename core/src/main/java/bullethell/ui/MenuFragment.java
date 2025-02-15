package bullethell.ui;

import bullethell.core.Core;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.ui.menu.*;

// todo: menu stuff
public class MenuFragment implements Fragment {
    public MenuDialog current, previous;

    public MenuDialog mainMenu, startStory, selectDifficulty, selectPlayer;

    @Override
    public void build(CWidgetGroup target) {
        mainMenu = new MainMenu();
        startStory = new SelectStage();
        selectDifficulty = new SelectDifficulty();
        selectPlayer = new SelectPlayer();

        target.add(mainMenu, startStory, selectDifficulty, selectPlayer);
        setMenu(mainMenu);
    }

    public void previousMenu() {
        setMenu(previous);
    }

    public void setMenu(MenuDialog dialog) {
        if(current == dialog) return;

        if(current != null) current.hide();

        previous = current;

        if(dialog == null) {
            current.hide();

            current = null;
            return;
        }

        this.current = dialog;
        Core.stage.setKeyboardFocus(current);
        current.show();
    }
}
