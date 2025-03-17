package bullethell.entity.trait;

import com.badlogic.gdx.math.Interpolation;

public interface Interpc {
    float fin();

    default float fout(){
        return 1f - fin();
    }

    default float fin(Interpolation i){
        return i.apply(fin());
    }

    default float fout(Interpolation i){
        return i.apply(fout());
    }
//    default float fout(float margin){
//        float f = fin();
//        if(f >= 1f - margin){
//            return 1f - (f - (1f - margin)) / margin;
//        }else{
//            return 1f;
//        }

//    }
}
