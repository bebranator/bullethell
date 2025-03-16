package bullethell.tools;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Packer {
    public static void execute() {
        TexturePacker.Settings settings = new TexturePacker.Settings();

        settings.maxWidth = settings.maxHeight = 4096;
        settings.combineSubdirectories = true;

        TexturePacker.process(settings, "assets-raw/sprites", "assets/sprites", "sprites.atlas");
    }
}
