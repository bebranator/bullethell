package bullethell.entity.trait;

import bullethell.utils.Time;
import com.badlogic.gdx.math.Vector2;

public interface Velocityc extends Movec {
    Vector2 velocity();

    float drag();

    default void updateVelocity() {
        /*
        velocity().scl(1f - drag() * Time.delta());

        if(this instanceof SolidTrait){
            ((SolidTrait)this).move(velocity().x * Time.delta(), velocity().y * Time.delta());
        }else{
            moveBy(velocity().x * Time.delta(), velocity().y * Time.delta());
        }
         */

        velocity().scl(1f - drag() * Time.delta);

        moveBy(velocity().x * Time.delta, velocity().y * Time.delta);
    }
}
