package bullethell.type;

import bullethell.game.spell.SpellOrientation;
import bullethell.graphics.Draw;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// stores portraits links, name, description
public class Hero {
    public final String name;
    public String description = "";
    // todo: portrait links

    public Hero(String name) {
        this.name = name;
    }

    public TextureRegion spellCardPortrait() {
        return Draw.white;
    }

    public SpellOrientation spellOrientation() {
        // portrait should move from left to right
        return SpellOrientation.right;
    }
}
