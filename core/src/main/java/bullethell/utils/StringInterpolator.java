package bullethell.utils;

import bullethell.log.Log;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;

public class StringInterpolator {
    private String target;
    private float maxTime, time = 0;

    public StringInterpolator() {

    }

    public void update() {
        time = Math.min(time + Time.delta, maxTime);
    }

    public void setString(String origin) {
        this.target = origin;
    }

    public void setTargetTime(float time) {
        this.maxTime = time;
    }

    // [0; 1]
    public void setProgress(float progress) {
//        time = progress * maxTime;
//        Log.info("e" + time);
//        Log.info("e" + maxTime);

        time = MathUtils.clamp(maxTime * progress, 0, maxTime);
    }

    public String get() {
        return target.substring(0,
            (int)(time / maxTime * target.length()));
    }
}
