package bullethell.core;

import com.badlogic.gdx.math.MathUtils;

public class Settings {
    public static float soundVolume = 0;
    public static float musicVolume = 1;

    public static float soundVolume(float value) {
        return MathUtils.clamp(soundVolume * value, 0, 1);
    }
    public static float musicVolume(float value) {
        return MathUtils.clamp(musicVolume * value, 0, 1);
    }
}
