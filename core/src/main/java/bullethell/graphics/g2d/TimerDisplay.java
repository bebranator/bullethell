package bullethell.graphics.g2d;

import bullethell.module.Styles;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import java.text.DecimalFormat;

// displays seconds and milliseconds
public class TimerDisplay extends CTable {
    static final DecimalFormat format = new DecimalFormat("00.00");
    private Label seconds, ms;

    private float time;
    private boolean displayTime;

    public TimerDisplay() {
        seconds = new Label("00", Styles.kellyLabel24);
        ms = new Label("00", Styles.kellyLabel16);

        add(seconds);
        add(ms).padLeft(4).padBottom(2).align(Align.bottom);
    }

    public void displayTime(boolean value) {
        this.displayTime = value;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateDisplay();
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

        seconds.setText(formatted.substring(0, 2));
        ms.setText(formatted.substring(3, 5));
    }
}
