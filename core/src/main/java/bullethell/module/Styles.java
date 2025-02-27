package bullethell.module;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class Styles {
    public static Label.LabelStyle kellyLabel24, kellyLabel24Outline, kellyLabel12, kellyLabel16;

    public static Label.LabelStyle defLabel;
    public static Window.WindowStyle defWindow;

    public static void init() {
        kellyLabel24 = new Label.LabelStyle() {{
            font = Fonts.kelly24;
            fontColor = Color.WHITE;
        }};
        kellyLabel24Outline = new Label.LabelStyle() {{
            font = Fonts.kelly24Outline;
            fontColor = Color.WHITE;
        }};
        kellyLabel12 = new Label.LabelStyle() {{
            font = Fonts.kelly12;
            fontColor = Color.WHITE;
        }};
        kellyLabel16 = new Label.LabelStyle() {{
            font = Fonts.kelly16;
            fontColor = Color.WHITE;
        }};

        defWindow = new Window.WindowStyle() {{
            titleFont = Fonts.kelly24;
            titleFontColor = Color.WHITE;
            background = Tex.black6;
        }};


        defLabel = kellyLabel24;
    }
}
