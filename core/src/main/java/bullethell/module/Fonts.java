package bullethell.module;

import bullethell.core.Core;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Fonts {
    public static BitmapFont kelly24, kelly12, kelly24Outline;
    public static final String characters = FreeTypeFontGenerator.DEFAULT_CHARS.concat(
        "éöóêåíãøùçõúôûâàïğîëäæıÿ÷ñìèòüáşÉÖÓÊÅÍÃØÙÇÕÚÔÛÂÀÏĞÎËÄÆß×ÑÌÈÒÜÁŞ" // ïîí
    );

    public static void load() {
        FreeTypeFontGenerator kellygen = new FreeTypeFontGenerator(Core.files.internal("font/KellySlab-Regular.ttf"));
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

        kelly12 = kellygen.generateFont(param12);
        kelly12.getData().markupEnabled = true;
    }
    static FreeTypeFontGenerator.FreeTypeFontParameter param24() {
        return new FreeTypeFontGenerator.FreeTypeFontParameter() {{
            characters = Fonts.characters;
            size = 24;
        }};
    }
}
