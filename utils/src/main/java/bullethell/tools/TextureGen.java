package bullethell.tools;

import com.badlogic.gdx.graphics.Pixmap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureGen {
    public static void execute() throws IOException {
        BufferedImage white = img(1, 1);
        white.setRGB(0, 0, 0xFFFFFFFF);

        ImageIO.write(white, "png", new File("../assets-raw/sprites/white.png"));
    }

    static BufferedImage img(int w, int h) {
        return new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
    }
}
