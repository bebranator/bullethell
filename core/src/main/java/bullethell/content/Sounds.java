package bullethell.content;

import bullethell.core.Core;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    public static Sound death, boom01;

    public static void init() {
        death = sound("se_pldead00");
        boom01 = sound("se_enep02");
    }

    static Sound sound(String name) {
        return Core.audio.newSound(Core.files.internal("sound/" + name + ".wav"));
    }
}
