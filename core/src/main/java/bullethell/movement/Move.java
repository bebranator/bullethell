package bullethell.movement;

import bullethell.entity.trait.Solidc;
import bullethell.func.Floats1;
import bullethell.utils.CPool;
import bullethell.utils.Tmp;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/*

bullet.move = Move.accelerated(bullet.move, 2 acceleration rate, );
bullet.move = Move.accelerated(bullet.move, (bullet) -> MathUtils.sin(bullet.time));
bullet.move = Move.linear(bullet.move);
bullet.move = Move.circle(bullet.move, ); // rotates around point

 */
public class Move {
    private static CPool<Move> movePool = new CPool<>(Move::new);

    static Move obtain() {
        return movePool.obtain();
    }

    protected Move() {

    }

    public Solidc target;

    public float maxSpeed, minSpeed, maxRotation, minRotation;
    public float speed;

    private float rotation; // should be same thing but id like that way
    private Vector2 direction, targetPoint;
    private Floats1<Solidc> speedSupplier = (properties) -> 0;
    private Floats1<Solidc> rotationSupplier = (properties) -> 0;

    public void update() {
        rotation = MathUtils.clamp(rotation + rotationSupplier.get(target), minRotation, maxRotation);
        rotationDeg(rotation);

        speed = MathUtils.clamp(speedSupplier.get(target), minSpeed, maxSpeed);

        Vector2 tmpv21 = Tmp.v21.set(direction).scl(speed);
        target.moveBy(tmpv21.x, tmpv21.y);
    }

    public Move speed(Floats1<Solidc> speed) {
        this.speedSupplier = speed;
        return this;
    }

    public Move rotation(Floats1<Solidc> rotation) {
        this.rotationSupplier = rotation;
        return this;
    }
    public Move linear(float speed) {
        speed(e -> 0);
        this.speed = speed;
        return this;
    }
    public Move direction(Vector2 cpy) {
        // should be normalized btw
        this.direction.set(cpy);
        rotation = direction.angleDeg();

        return this;
    }

    public void rotationDeg(float rotation) {
        this.rotation = rotation;
        direction.set(1, 0).rotateDeg(rotation);
    }

    public static Move speed(Move move, Floats1<Solidc> acceleration) {
        move.speed(acceleration);
        return move;
    }

    public void setTarget(Solidc properties, boolean resetSuppliers) {
        if(resetSuppliers) {
            speed((e) -> 0);
            rotation((e) -> 0);
        }
        this.target = properties;
    }

    public static Move linear(Solidc target, float speed) {
        Move move = obtain();
        move.target = target;
        move.speed(e -> 0);
        move.rotation(e -> 0);
        move.speed = speed;
        return move;
    }

    /** obtains new move from pool and sets target as move's target */
    public static Move speed(Solidc target, Floats1<Solidc> properties) {
        Move obtain = obtain();
        obtain.target = target;
        obtain.speed(properties);
        return obtain;
    }
}
