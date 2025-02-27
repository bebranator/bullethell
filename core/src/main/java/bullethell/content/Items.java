package bullethell.content;

import bullethell.entity.type.ItemEntity;
import bullethell.game.GameStats;
import bullethell.type.ItemType;

public class Items extends Content {
    public static ItemType powerSmall, powerBig;

    @Override
    public void init() {
        powerSmall = new ItemType("poweritem-small");
        powerBig = new ItemType("poweritem");
    }

    void depositPower(ItemEntity entity) {
    }
}
