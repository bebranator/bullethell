package bullethell.utils;

public class Mathc {
    public static float fapproachAsymptotic(float val, float target, float rate, float epsilon) {
        if(fabsf(val - target) < epsilon || rate >= 1.0f) {
            return target;
        }

        // lerp then
        return val + (target - val) * rate;
    }

    public static float fabsf(float value) {
        return Math.abs(value);
    }
}
