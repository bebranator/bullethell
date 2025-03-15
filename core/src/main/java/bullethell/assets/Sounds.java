package bullethell.assets;

import bullethell.core.Core;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;

public class Sounds {
    public static Sound death, boom02, ok, boon, select, spell;

    public static IntMap<Sound> idMap = new IntMap<>();

    public static void load() {
//        death = sound("pldeath");
//        boom02 = sound("boom02");
//        ok = sound("ok");
//        boon = sound("boon");
//        select = sound("select");
//        spell = sound("spell");
        load("pldeath",
            "boom02",
            "ok",
            "boon",
            "select",
            "spell"
            );
    }

    public static void init() {
        init("pldeath",
            "boom02",
            "ok",
            "boon",
            "select",
            "spell"
            );
        death = get();
        boom02 = get();
        ok = get();
        boon = get();
        select = get();
        spell = get();
    }

    static Sound sound(String name) {
        return Core.audio.newSound(Core.files.internal("sound/" + name + ".wav"));
    }

    static int i;
    static void load(String... name) {
        for (String s : name) {
            idMap.put(i++, null);
            Assets.load("sound/" + s + ".wav", Sound.class);
        }
    }
    static int initialized = 0;
    static void init(String... names) {
        for(String n : names) {
            idMap.put(initialized++, Assets.get("sound/" + n + ".wav", Sound.class));
        }
        i = 0;
    }
    static Sound get() {
        return idMap.get(i++);
    }
}
