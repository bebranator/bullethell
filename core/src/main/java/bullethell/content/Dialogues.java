package bullethell.content;

import bullethell.game.dialog.GameDialog;

public class Dialogues {
    public static GameDialog tutorial = GameDialog.begin((e) -> {
        e.text("dialog 001");
        e.hero(Chars.bazarov);
        e.time(4f);

        e.text("lmao lol");
        e.hero(Chars.meiling);
        e.time(3f);

        e.text("i still speak");
        e.hero(Chars.meiling);
        e.time(8f);
    });
}
