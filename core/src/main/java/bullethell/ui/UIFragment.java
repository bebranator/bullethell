package bullethell.ui;

import bullethell.game.spell.SpellCard;
import bullethell.graphics.g2d.CImage;
import bullethell.graphics.g2d.CLabel;
import bullethell.graphics.g2d.CTable;
import bullethell.graphics.g2d.CWidgetGroup;
import bullethell.type.BossType;
import com.badlogic.gdx.utils.Align;

// todo: show boss health, spell card visuals
public class UIFragment implements Fragment {
    private CWidgetGroup spellPortraitGroup;
    private CImage spellPortrait;

    private CLabel spellName;
    @Override
    public void build(CWidgetGroup target) {
        spellPortraitGroup = new CWidgetGroup();

        spellName = new CLabel();

        spellPortraitGroup.setFillParent(false);

        target.addActor(spellPortraitGroup);
    }

    public void showSpell() {

    }
}
