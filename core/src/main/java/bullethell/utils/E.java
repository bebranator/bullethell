package bullethell.utils;

import bullethell.core.Vars;

public class E {
    public static float byDifficulty(float a, float b, float c, float d, float h) {
        return switch (Vars.game.difficulty) {
            case easy -> a;
            case normal -> b;
            case hard -> c;
            case expert -> d;
            case lunatic -> h;
        };
    }
}
