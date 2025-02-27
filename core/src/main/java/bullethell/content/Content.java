package bullethell.content;

import bullethell.struct.CArray;

public abstract class Content {
    public abstract void init();

    public static void init(Content... contents) {
        for (Content content : contents) {
            content.init();
        }
    }
}
