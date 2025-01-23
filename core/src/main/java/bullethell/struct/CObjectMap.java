package bullethell.struct;

import bullethell.func.Supp;
import com.badlogic.gdx.utils.ObjectMap;

public class CObjectMap<K, V> extends ObjectMap<K, V> {
    public V get(K key, Supp<V> supplier){
        V val = get(key);
        if(val == null){
            put(key, val = supplier.get());
        }
        return val;
    }
}
