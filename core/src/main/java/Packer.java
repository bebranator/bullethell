import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Packer {
    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();

        settings.maxWidth = settings.maxHeight = 4096;

        TexturePacker.process(settings, "assets-raw/sprites", "assets", "sprites.atlas");
    }
}
