package bullethell.utils;

import bullethell.log.DefaultLoggerProvider;
import bullethell.log.Log;
import com.badlogic.gdx.math.Interpolation;
import org.junit.jupiter.api.Test;

public class StringInterpolatorTest {
    @Test
    public void progressTest() {
        // works as intended
        Log.setLogger(new DefaultLoggerProvider());
        StringInterpolator interpolator = new StringInterpolator();

        interpolator.setString("LUNATIC MODE");
        interpolator.setTargetTime(3f);

        interpolator.setProgress(20f);

        Log.info(interpolator.get());
    }
}
