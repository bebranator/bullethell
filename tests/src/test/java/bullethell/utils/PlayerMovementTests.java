package bullethell.utils;

import bullethell.log.DefaultLoggerProvider;
import bullethell.log.Log;
import com.badlogic.gdx.utils.Align;
import org.junit.jupiter.api.Test;

public class PlayerMovementTests {
    // 0 == no movement
    // if left && right: write 0 to both
    // if up && down: write 0 to both
    // we need only 4 bits for stuff
    static int LEFT = 0x1, RIGHT = 0x1 << 1, UP = 0x1 << 2, DOWN = 0x1 << 3;

    // get bit from number.
    // return ((num & (1 << i)) != 0);

    // set bit
    //int setBit(int num, int i)
    //{
    //    // Sets the ith bit and return
    //    // the updated value
    //    return num | (1 << i);
    //}
//    static int clearBit(int num, int i)
//    int mask = ~(1 << i);
//    return num & mask;
    @Test
    public void bytes() {
        Log.setup();
        Log.info(Integer.toBinaryString(LEFT) + "=left");
        Log.info(Integer.toBinaryString(RIGHT) + "=right");
        Log.info(Integer.toBinaryString(UP) + "=up");
        Log.info(Integer.toBinaryString(DOWN) + "=down");
    }

    @Test
    public void setting() {
        Log.setup();
        // means: left and right
        int number = 0b0011;
        //    return num | (1 << i);
        Log.info(string(number));
    }

    public String string(int input) {
        StringBuilder src = new StringBuilder();
        // return ((num & (1 << i)) != 0);
        if((input & LEFT) != 0) src.append("LEFT ");
        if((input & RIGHT) != 0) src.append("RIGHT ");
        if((input & DOWN) != 0) src.append("DOWN ");
        if((input & UP) != 0) src.append("UP ");
        return src.toString();
    }
}
