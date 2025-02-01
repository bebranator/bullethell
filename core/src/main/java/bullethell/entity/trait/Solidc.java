package bullethell.entity.trait;

import com.badlogic.gdx.math.Rectangle;

public interface Solidc extends Entityc {
    boolean intersect(Rectangle rect);
}
