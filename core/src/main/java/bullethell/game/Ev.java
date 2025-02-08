package bullethell.game;

import bullethell.game.spell.SpellCard;
import bullethell.game.spell.SpellCardEndInfo;

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

    // when we spell card ends (runs out of time/boss dies)
    public static class SpellCardEndEvent {
        private static final SpellCardEndEvent ev = new SpellCardEndEvent();

        public SpellCardEndInfo info;
        public SpellCard card;

        public static SpellCardEndEvent get(SpellCardEndInfo info, SpellCard spell) {
            ev.info = info;
            ev.card = spell;

            return ev;
        }
    }

    public static class StageBeginEvent {}

    public static class PlayerDeathEvent {}

    public static class ClientInit {}
    public static class ClientLoad {}
}
