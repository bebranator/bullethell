package bullethell.type;

public class ItemType {
    public final String name;
    public float size = 32;

    public Runnable pickedUp;

    public ItemType(String name) {
        this.name = name;
    }
    public ItemType(String name, Runnable runnable) {
        this(name);
        pickedUp = runnable;
    }

    public void pickedUp() {
    }

    public float size() {
        return size;
    }
}
