package bullethell.content;

import bullethell.type.Hero;
import com.badlogic.gdx.utils.Array;

// class for content
public class Heroes extends Content {
    public static Hero bazarov, zheka, bob, evilBob;
    public static final Array<Hero> playableHeroes = new Array<>();

    @Override
    public void init() {
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
