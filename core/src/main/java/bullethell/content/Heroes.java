package bullethell.content;

import bullethell.type.Hero;
import com.badlogic.gdx.utils.Array;

// class for content
public class Heroes {
    public static Hero bazarov, zheka, bob, evilBob;
    public static Array<Hero> playableHeroes = new Array<>();

    public static void init() {
        bazarov = new Hero("bazarov") {{
            description = "#hero.description.bazarov";
        }};
        zheka = new Hero("zheka") {{
            description = "#hero.description.me";
        }};

        bob = new Hero("bob") {{
            description = "bob";
        }};
        evilBob = new Hero("evilbob") {{
            description = "злой bob";
        }};

        playableHeroes.addAll(bazarov, zheka);
    }
}
