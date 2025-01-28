package bullethell.utils;

// direction
public class Dir {
    public static final int
        LEFT = 0x1,
        RIGHT = 0x1 << 1,
        UP = 0x1 << 2,
        DOWN = 0x1 << 3;

    public static boolean isLeft(int input) {
        return (input & LEFT) != 0;
    }
    public static boolean isRight(int input) {
        return (input & RIGHT) != 0;
    }
    public static boolean isDown(int input) {
        return (input & DOWN) != 0;
    }
    public static boolean isUp(int input) {
        return (input & UP) != 0;
    }
}
