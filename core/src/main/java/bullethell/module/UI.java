package bullethell.module;

import bullethell.assets.Assets;
import bullethell.assets.Sounds;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.graphics.g2d.CStage;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.type.BossType;
import bullethell.ui.GameFragment;
import bullethell.ui.MenuFragment;
import bullethell.ui.UIFragment;
import bullethell.ui.dialog.DialogueDialog;
import bullethell.ui.dialog.LoadingDialog;
import bullethell.ui.dialog.PauseDialog;
import bullethell.ui.dialog.StageResultsDialog;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class UI implements IModule {
    public CWidgetGroup menuGroup, uiGroup, gameGroup;

    public MenuFragment menuFragment;
    public UIFragment uiFragment;
    public GameFragment gameFragment;

    public LoadingDialog loadingDialog;
    public PauseDialog pauseDialog;
    public DialogueDialog dialogueDialog;
    public StageResultsDialog stageResultsDialog;

    @Override
    public void create() {
        Sounds.load();
        Fonts.preInit();
        Assets.load("sprites/sprites.atlas", TextureAtlas.class);
        Assets.loaded(this::init);
    }

    public void init() {
        Sounds.init();
        Fonts.init();
        Styles.init();
        Tex.init();

        CStage stage = Core.stage;

        stage.table((e) -> {
            e.top().right();
            e.label(() -> "fps: " + Core.graphics.getFramesPerSecond(), Styles.kellyLabel16);
            e.row();
            e.label(() -> "render calls: " + ((SpriteBatch)Core.batch).renderCalls, Styles.kellyLabel16);
        });
        menuGroup = new CWidgetGroup();
        uiGroup = new CWidgetGroup();
        gameGroup = new CWidgetGroup();
        menuGroup.setFillParent(true);
        uiGroup.setFillParent(true);
        gameGroup.setFillParent(true);

        loadingDialog = new LoadingDialog();
        pauseDialog = new PauseDialog();
        dialogueDialog = new DialogueDialog();
        stageResultsDialog = new StageResultsDialog();

//        menuGroup.visible(Vars::menu);
//        uiGroup.visible(() -> Vars.inGame() || Vars.paused());
//        gameGroup.visible(Vars::inGame);
        // todo: manual hide

        stage.add(menuGroup, uiGroup, gameGroup);

        menuFragment = new MenuFragment();
        menuFragment.build(menuGroup);

        uiFragment = new UIFragment();
        uiFragment.build(uiGroup);

        gameFragment = new GameFragment();
        gameFragment.build(gameGroup);
        uiGroup.setVisible(false);
    }

    public void menu() {
//        Core.stage.setKeyboardFocus(menuGroup);
        menuFragment.setMenu(menuFragment.mainMenu);
        menuGroup.setVisible(true);
        uiGroup.setVisible(false);
        gameGroup.setVisible(false);
    }
    public void game() {
        Core.stage.setKeyboardFocus(null);
        menuGroup.setVisible(false);
        uiGroup.setVisible(true);
        gameGroup.setVisible(true);
    }

    public void spell(BossType boss) {
        // summon spell card display in ui group
        showSpellUi();
    }

    public void showBossDisplay() {
    }

    public void hideBossDisplay() {

    }

    public void hideSpellUi() {

    }
    public void showSpellUi() {
        // do sound
        Vars.sounds.playSound(Sounds.spell, .5f);
    }

    public void spellBonus(int bonus) {
        if(bonus == 0) {
            // failed bonus
            uiFragment.failedSpell();
        }else {
            uiFragment.spellBonus();
        }
    }

    public void loading(float time, Runnable run) {
    }

    @Override
    public void render() {
        Core.stage.act();
        Core.stage.draw();
    }
}
