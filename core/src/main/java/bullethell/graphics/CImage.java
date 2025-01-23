package bullethell.graphics;

import bullethell.core.Core;
import bullethell.utils.Tmp;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.github.czyzby.kiwi.util.gdx.scene2d.Actors;

public class CImage extends Image {
    public CImage(Drawable draw) {
        super(draw);
    }
    public void add() {
        Core.stage.add(this);
    }

    public void center() {
        Stage stage = getStage();

        Vector2 position = Tmp.v21.set((stage.getWidth() - getWidth()) / 2, (stage.getHeight() - getHeight()) / 2);

        setPosition(position.x, position.y);
    }
}
