package bullethell.movement;

import bullethell.entity.trait.Movec;
import bullethell.utils.Tmp;
import com.badlogic.gdx.math.Vector2;

public class Mover {
    /*
    cmplx move_update(cmplx *restrict pos, MoveParams *restrict p) {
        MoveParams o = *p;
        cmplx orig_velocity = o.velocity;
    	*pos += orig_velocity;
        o.velocity = o.acceleration + cmul_finite(o.retention, o.velocity);

        if(o.attraction) {
            cmplx av = o.attraction_point - *pos;

            if(LIKELY(o.attraction_exponent == 1)) {
                o.velocity += cmul_finite(o.attraction, av);
            } else {
                real m = cabs2(av);
                assume(m >= 0);
                m = pow(m, o.attraction_exponent - 0.5);
                assert(isfinite(m));
                o.velocity += cmul_finite(o.attraction, av * m);
            }
        }

        p->velocity = o.velocity;
        return orig_velocity;
    }
     */
    public static Vector2 update(Movec pos, MovementParams params) {
        MovementParams p = params;

        Vector2 origVelocity = new Vector2(p.velocity);
        pos.moveBy(origVelocity.x, origVelocity.y);
        p.velocity.set(Tmp.v23.set(p.acceleration).add(cmul(p.retention, p.velocity)));

        if(!p.attraction.isZero()) {
            Vector2 av = Tmp.v22.set(p.attractionPoint).sub(pos.position());

            if(p.attractionExponent == 1) {
                p.velocity.add(cmul(p.attraction, av));
            } else {
                float m = cabs2(av);
                m = (float) Math.pow(m, p.attractionExponent - .5f);
                p.velocity.add(cmul(p.attraction, av.scl(m)));
            }
        }
        params.velocity.set(p.velocity);
        params.difference.set(p.velocity).sub(origVelocity);

        return origVelocity;
    }
    /*
INLINE cmplx cmul_finite(cmplx a, cmplx b) {
	double ra = ASSUME_FINITE(re(a));
	double ia = ASSUME_FINITE(im(a));
	double rb = ASSUME_FINITE(re(b));
	double ib = ASSUME_FINITE(im(b));
	return CMPLX(ra * rb - ia * ib, ra * ib + ia * rb);
}
     */
    static Vector2 cmul(Vector2 a, Vector2 b) {
        return new Vector2(a.x * b.x - a.y * b.y, a.x * b.y + a.y * b.x);
    }
    /*
INLINE double cabs2(cmplx c) {
    return re(c) * re(c) + im(c) * im(c);
}
    */
    static float cabs2(Vector2 b) {
        return b.x * b.x + b.y * b.y;
    }
}
