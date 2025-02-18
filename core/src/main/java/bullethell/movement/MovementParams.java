package bullethell.movement;

import bullethell.utils.Tmp;
import com.badlogic.gdx.math.Vector2;

/*
oh fuck yeah we use taisei project code
ill come up with better solution later

typedef struct cmplx {
    float x;
    float y;
} cmplx;

typedef struct MoveParams {
	cmplx velocity, acceleration, retention;
	cmplx attraction;
	cmplx attraction_point;
	real attraction_exponent;
} MoveParams;
 */
public class MovementParams {
    public final Vector2
        velocity = new Vector2(),
        acceleration = new Vector2(),
        retention = new Vector2();
    public final Vector2
        attraction = new Vector2(),
        attractionPoint = new Vector2();
    public float attractionExponent = 0;
    public MovementParams reset() {
        velocity.setZero();
        acceleration.setZero();
        retention.setZero();
        attraction.setZero();
        attractionPoint.setZero();
        attractionExponent = 0;
        return this;
    }

    public MovementParams linear(float velocityX, float velocityY) {
        this.velocity.set(velocityX, velocityY);
        this.retention.set(1, 0);
        attraction.setZero();
        attraction.setZero();
        return this;
    }
    public MovementParams linear(Vector2 velocity) {
        this.velocity.set(velocity);
        this.retention.set(1, 0);
        return this;
    }
    public MovementParams rotateVelocity(float deg) {
        this.velocity.rotateDeg(deg);
        return this;
    }
    public MovementParams rotateAcceleration(float deg) {
        this.acceleration.rotateDeg(deg);
        return this;
    }
    public MovementParams rotateRetention(float deg) {
        this.retention.rotateDeg(deg);
        return this;
    }
    public MovementParams rotate(float deg) {
        this.acceleration.rotateDeg(deg);
        this.velocity.rotateDeg(deg);
        return this;
    }
    public MovementParams accelerated(float velX, float velY, float accelX, float accelY) {
        this.velocity.set(velX, velY);
        this.acceleration.set(accelX, accelY);
        this.retention.set(1, 0);
        return this;
    }
    public MovementParams asymptotic(float ax, float ay, float bx, float by, float rx, float ry) {
        this.velocity.set(ax, ay);
        this.acceleration.set(bx * (1 - rx), by * (1 - ry));
        this.retention.set(rx, ry);
        return this;
    }
    public MovementParams asymptoticHalfLife(float ax, float ay, float bx, float by, float life) {
        this.velocity.set(ax, ay);
        this.acceleration.set(bx, by);
        this.retention.set((float) Math.pow(2, (-1 / life)), 0);
        return this;
    }
    public MovementParams asymptoticSimple(float vx, float vy, float boost) {
        float retention = 0.8f;
        return asymptotic(vx * (1 + boost), vy * (1 + boost), vx, vy, retention, 0);
    }
    public MovementParams towards(float vx, float vy, float tx, float ty, float ax, float ay) {
        velocity.set(vx, vy);
        attraction.set(ax, ay);
        attractionPoint.set(tx, ty);
        attractionExponent = 1;
        return this;
    }
}
