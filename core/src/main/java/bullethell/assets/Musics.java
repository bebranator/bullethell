package bullethell.assets;

import bullethell.core.Core;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;

public class Musics {
    public static Music bad_apple, stage6_boss;

    public static final IntMap<Music> idMap = new IntMap<>();
    public static final ObjectMap<String, Music> nameMap = new ObjectMap<>();
    private static FileHandle[] musics;

    public static void load() {
        musics = Core.files.internal("music").list();
        for(FileHandle handle : musics) {
            Assets.load("music/" + handle.name(), Music.class);
        }
    }

    public static void init() {
        Music e;
        for(FileHandle handle : musics) {
            idMap.put(i++, e = Assets.get("music/" + handle.name(), Music.class));
            nameMap.put(handle.nameWithoutExtension(), e);
        }
        i = 0;
        bad_apple = get();
        stage6_boss = get();
    }

    static int i = 0;
    static Music get() {
        return idMap.get(i++);
    }
}
