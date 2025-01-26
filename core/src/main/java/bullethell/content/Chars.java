package bullethell.content;

import bullethell.type.Hero;

// class for content
public class Chars {
    public static Hero bazarov, meiling;

    public static void init() {
        bazarov = new Hero("bazarov") {{
            description = "n";
        }};

        meiling = new Hero("Meiling") {{
            description = "lmao";
        }};
    }
}
