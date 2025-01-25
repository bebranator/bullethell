package bullethell.utils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;

public class StringInterpolator {
    private String target;
    private float maxTime, time;
    private Interpolation interpolation;

    public StringInterpolator() {

    }

    public void update() {
        time = Math.min(time + Time.delta, maxTime);
    }

    public void setString(String origin) {

    }

    // [0; 1]
    public void setProgress(float progress) {
        time = MathUtils.clamp(maxTime * progress, 0, maxTime);
    }

    public void setInterpolation(Interpolation interp) {
    }

    public String get() {
        return target.substring(0,
            (int)(interpolation.apply(time / maxTime) * target.length()));
    }
}
