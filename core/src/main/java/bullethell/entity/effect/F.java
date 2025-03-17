package bullethell.entity.effect;

import static bullethell.entity.effect.Effects.*;

public class F {
    private static Effect effect(float lifetime, Drawer drawer) {
        return new Effect(lifetime, drawer);
    }

    public static final Effect blankTest = effect(60 * 6, (draw) -> {
    });
}
