package bullethell.struct;

import com.badlogic.gdx.utils.Array;

public class CArray<T> extends Array<T> {
    public CArray() {
        super();
    }

    @SafeVarargs
    public CArray(T... entries) {
        super(entries);
    }

    public T getOrNull(int index) {
        try {
            return super.get(index);
        }catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }
    public T getOr(int index, T or) {
        try {
            return super.get(index);
        }catch (IndexOutOfBoundsException ex) {
            return or;
        }
    }
    // puts or at index
    public T getOrPut(int index, T or) {
        try {
            return super.get(index);
        }catch (IndexOutOfBoundsException ex) {
            super.insert(index, or);
            return or;
        }
    }
}
