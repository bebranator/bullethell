package bullethell.module;

import bullethell.assets.Assets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {
    public static BitmapFont kelly24, kelly12, kelly24Outline, kelly16;
    public static final String characters = FreeTypeFontGenerator.DEFAULT_CHARS.concat(
        "éöóêåíãøùçõúôûâàïğîëäæıÿ÷ñìèòüáşÉÖÓÊÅÍÃØÙÇÕÚÔÛÂÀÏĞÎËÄÆİß×ÑÌÈÒÜÁŞ");

    public static void load() {
        Assets.load("font/KellySlab-Regular.ttf", FreeTypeFontGenerator.class);
    }

    public static void init() {
        FreeTypeFontGenerator kellygen = Assets.get("font/KellySlab-Regular.ttf", FreeTypeFontGenerator.class);
        FreeTypeFontGenerator.FreeTypeFontParameter param24 = param24();
        var param24Outline = param24();
        param24Outline.shadowColor = Color.GRAY;
        param24Outline.borderWidth = 4;
        param24Outline.borderColor = Color.BLACK;
        param24Outline.spaceX = -2;

        kelly24 = kellygen.generateFont(param24);
        kelly24.getData().markupEnabled = true;

        kelly24Outline = kellygen.generateFont(param24Outline);
        kelly24Outline.getData().markupEnabled = true;

        var param12 = param24();
        param12.size = 12;

        var param16 = param24();
        param16.size = 16;

        kelly12 = kellygen.generateFont(param12);
        kelly12.getData().markupEnabled = true;

        kelly16 = kellygen.generateFont(param16);
        kelly16.getData().markupEnabled = true;

        kellygen.dispose();
    }
    static FreeTypeFontGenerator.FreeTypeFontParameter param24() {
        return new FreeTypeFontGenerator.FreeTypeFontParameter() {{
            characters = Fonts.characters;
            size = 24;
        }};
    }
}
