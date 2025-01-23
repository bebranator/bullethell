package bullethell.game;

public enum State {
    menu, pause, inGame;

    // is this state
    public boolean state(State state) {
        return this == state;
    }

    public boolean pause() {
        return this == pause;
    }

    public boolean inGame() {
        return this == inGame;
    }
    public boolean menu() {
        return this == menu;
    }
}
