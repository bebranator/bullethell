package bullethell.game;

public class Ev {
    public static class StateChange {
        private static final StateChange INSTANCE = new StateChange();

        public State previous, current;

        public static StateChange get(State prev, State current) {
            INSTANCE.previous = prev;
            INSTANCE.current = current;

            return INSTANCE;
        }
    }

    public static class ClientInit {}
    public static class ClientLoad {}

    public enum Trigger {
        clientInit
    }
}
