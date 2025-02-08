package bullethell.graphics.g2d;

import bullethell.func.Floats;
import bullethell.graphics.action.LabelFontScale;
import bullethell.module.Styles;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import java.text.DecimalFormat;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

// displays seconds and milliseconds
public class TimerDisplay extends CTable {
    static final DecimalFormat format = new DecimalFormat("00.00");
    private Label seconds, ms;

    private float time = 99.99f;
    private boolean displayTime;
    private Floats supplier;

    public TimerDisplay() {
        seconds = new Label("00", Styles.kellyLabel24);
        ms = new Label("00", Styles.kellyLabel16);

        add(seconds);
        add(ms).padLeft(4).padBottom(2).align(Align.bottom);
    }

    public void displayTime(boolean value) {
        this.displayTime = value;
    }

    public void timeUpdater(Floats supplier) {
        this.supplier = supplier;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateDisplay();
        if(supplier != null) setTime(supplier.get());
    }

    public void setTime(float time) {
        this.time = MathUtils.clamp(time, 0, 99.99f);
        // divide by fps
    }
    public void updateDisplay() {
        if(!displayTime) {
            seconds.setText("--");
            ms.setText("--");
            return;
        }

        String formatted = format.format(time);

        String secs = formatted.substring(0, 2);
        String millis = formatted.substring(3, 5);

        // todo: dumb timer thing
//        if(time <= 10) {
//            int secValue = Character.getNumericValue(secs.charAt(1));
//
//            seconds.addAction(lowTime(secValue));
//        }

        seconds.setText(secs);
        ms.setText(millis);
    }
    final Color color = new Color();
    Action lowTime(int value) {
        color.set(.5f + value / 18f, 0, 0, 1f);
        return parallel(color(color, .5f), sequence(
            LabelFontScale.labelFontScale(.8f, 3f),
            LabelFontScale.labelFontScale(.8f, 1f)
        ));
//        return null;
    }
}
