package bullethell.type;

import bullethell.core.Core;
import bullethell.entity.type.Player;
import bullethell.game.spell.SpellOrientation;
import bullethell.graphics.Draw;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

// stores portraits links, name, description
// todo: remove
public class Hero {
    private final int resolutionX = 32, resolutionY = 48;
    public final String name;
    public String description = "";
    // todo: portrait links
    public TextureRegion portrait;
    public TextureRegionDrawable portraitDrawable;
    public Array<TextureRegion> baseSprites = new Array<>();
    public Array<TextureRegion> movementSprites = new Array<>();
    public int movementSpritesAmount = 8; // for each direction (left and right)

    public Hero(String name) {
        this.name = name;
        this.portrait = Core.atlas.findRegion(name + "-portrait") == null
            ? Draw.white : Core.atlas.findRegion(name + "-portrait");
        this.portraitDrawable = new TextureRegionDrawable(portrait);

        // do idle sprites
        if(Core.atlas.findRegion(name + "-idle") != null) {
            TextureRegion idleAtlas = Core.atlas.findRegion(name + "-idle");

            TextureRegion[] split = idleAtlas.split(resolutionX, resolutionY)[0];
            baseSprites.addAll(split);
        }
        // movement sprites
        if(Core.atlas.findRegion(name + "-left") != null) {

        }
    }

    public TextureRegion portrait() {
        return portrait;
    }
    public TextureRegionDrawable portraitDrawable() {
        return portraitDrawable;
    }

    public SpellOrientation spellOrientation() {
        // portrait should move from left to right
        return SpellOrientation.right;
    }

    public void draw(Player player) {
//        boolean flip = player.axisX <= 0;
    }
}
