package bullethell.utils;

import bullethell.func.Supp;
import com.badlogic.gdx.utils.Pool;

public class CPool<T> extends Pool<T> {
    private Supp<T> constructor;

    public CPool(Supp<T> constructor) {
        this.constructor = constructor;
    }

    @Override
    protected T newObject() {
        return constructor.get();
    }
}
