package bullethell.assets;

import bullethell.core.Core;
import bullethell.log.Log;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.github.czyzby.kiwi.util.collection.IgnoreCaseStringMap;

import java.util.HashMap;

// todo: make it better
public class Sounds {
    public static Sound death, boom02, ok, boon, select, spell;

    public static final IntMap<Sound> idMap = new IntMap<>();
    public static final ObjectMap<String, Sound> nameMap = new ObjectMap<>();
    private static FileHandle[] sounds;

    public static void load() {
        sounds = Core.files.internal("sound").list();
        for(FileHandle handle : sounds) {
            Assets.load("sound/" + handle.name(), Sound.class);
        }
    }

    public static void init() {
        Sound e;
        for(FileHandle handle : sounds) {
            idMap.put(i++, e = Assets.get("sound/" + handle.name(), Sound.class));
            nameMap.put(handle.nameWithoutExtension(), e);
        }
        i = 0;
        boom02 = get();
        boon = get();
        ok = get();
        death = get();
        select = get();
        spell = get();
    }

    static int i = 0;
    static Sound get() {
        return idMap.get(i++);
    }
}
