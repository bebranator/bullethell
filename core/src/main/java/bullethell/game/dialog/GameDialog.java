package bullethell.game.dialog;

import bullethell.func.Cons;

/*
example of dialog:

static GameDialog dial_stage_4 = GameDialog.begin(builder -> {
    builder.time(.5f); // 0.5 seconds to fully end line
    builder.hero(raymoo);
    builder.text("Ты кто????");
    builder.finished().text("да");

    builder.time(.5f);
    builder.hero(Chars.bazarov);
    builder.text("ТЫ КТО???");

    builder.time();
    builder.hero(raymoo);
    builder.text("хз пон");
    // signals that dialog is finished, and we need to panic
    builder.finished().consumed();
});
 */
public class GameDialog {
    public static GameDialog begin(Cons<GameDialogBuilder> builderCons) {
        return new GameDialogBuilder().get(builderCons).build();
    }

    // todo: string interpolator (show only part)
    public void update() {

    }
}
