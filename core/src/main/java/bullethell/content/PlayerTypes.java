package bullethell.content;

import bullethell.type.PlayerType;

public class PlayerTypes extends Content {
    public static PlayerType bazarov, seija;

    @Override
    public void init() {
        bazarov = new PlayerType("bazarov") {{

        }};

        seija = new PlayerType("seija") {{

        }};
    }
}
