package bullethell.content;

import bullethell.core.Core;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    public static Sound death, boom02, ok, boon, select, spell;

    public static void init() {
        death = sound("pldeath");
        boom02 = sound("boom02");
        ok = sound("ok");
        boon = sound("boon");
        select = sound("select");
        spell = sound("spell");
    }

    static Sound sound(String name) {
        return Core.audio.newSound(Core.files.internal("sound/" + name + ".wav"));
    }
}
