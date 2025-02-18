package bullethell.utils;

import bullethell.func.Supp;
import bullethell.struct.CObjectMap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

// todo: obtain, return
public class CPools {
    private static final CObjectMap<Class, Pool> typePools = new CObjectMap<>();

    private CPools(){
    }

    /**
     * Returns a new or existing pool for the specified type, stored in a Class to {@link Pool} map. Note that the max size is ignored for some reason.
     * if this is not the first time this pool has been requested.
     */
    public static <T> Pool<T> get(Class<T> type, Supp<T> supplier, int max){
        Pool<T> pool = typePools.get(type);
        if(pool == null){
            pool = new CPool<>(4, max, supplier);
            typePools.put(type, pool);
        }
        return pool;
    }

    /**
     * Returns a new or existing pool for the specified type, stored in a Class to {@link Pool} map. The max size of the pool used
     * is 5000.
     */
    public static <T> Pool<T> get(Class<T> type, Supp<T> supplier){
        return get(type, supplier, 5000);
    }

    public static <T> void set(Class<T> type, Pool<T> pool){
        typePools.put(type, pool);
    }

    public static synchronized <T> T obtain(Class<T> type, Supp<T> supplier){
        return get(type, supplier).obtain();
    }

    public static synchronized void free(Object object){
        if(object == null) throw new IllegalArgumentException("Object cannot be null.");
        Pool pool = typePools.get(object.getClass());
        if(pool == null) return; // Ignore freeing an object that was never retained.
        pool.free(object);
    }

    public static void freeAll(Array objects){
        freeAll(objects, false);
    }

    public static void freeAll(Array objects, boolean samePool){
        if(objects == null) throw new IllegalArgumentException("Objects cannot be null.");
        Pool pool = null;
        for(int i = 0, n = objects.size; i < n; i++){
            Object object = objects.get(i);
            if(object == null) continue;
            if(pool == null){
                pool = typePools.get(object.getClass());
                if(pool == null) continue; // Ignore freeing an object that was never retained.
            }
            pool.free(object);
            if(!samePool) pool = null;
        }
    }
}
