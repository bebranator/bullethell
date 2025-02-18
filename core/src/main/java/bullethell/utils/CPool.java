package bullethell.utils;

import bullethell.func.Supp;
import com.badlogic.gdx.utils.Pool;

public class CPool<T> extends Pool<T> {
    private final Supp<T> constructor;

    public CPool(int initial, int max, Supp<T> constructor) {
        super(initial, max);
        this.constructor = constructor;
    }

    @Override
    protected T newObject() {
        return constructor.get();
    }
}
