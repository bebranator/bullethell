package bullethell.core;

import bullethell.entity.Arena;
import bullethell.entity.Entities;
import bullethell.entity.EntityGroup;
import bullethell.entity.type.Bullet;
import bullethell.entity.type.Player;
import bullethell.game.Ev;
import bullethell.game.State;
import bullethell.game.dialog.DialogueManager;
import bullethell.module.Asset;
import bullethell.module.Control;
import bullethell.module.Renderer;
import bullethell.module.UI;

public class Vars {
    private static State state;
    public static UI ui;
    public static Renderer renderer;
    public static Control control;

    public static Entities entities;
    public static Arena arena;

    public static EntityGroup<Bullet> enemyBullets;
    public static EntityGroup<Player> players;
    // why we do other group for player lmao
    public static Player player;

    public static DialogueManager dialogue;

    public static void init() {
        setState(State.menu);
        entities = new Entities();
        arena = new Arena(40, 40, 1000, 1000);

        dialogue = new DialogueManager();

        players = entities.getGroup(Player.class);
        enemyBullets = entities.getGroup(Bullet.class);
        player = new Player();
        player.add();
    }

    public static void setState(State newState) {
        Events.fire(Ev.StateChange.get(state, newState));
        state = newState;
        // todo: events
    }

    public static boolean state(State state) {
        // fun
        return state.state(state);
    }

    public static boolean menu() {
        return state.menu();
    }
    public static boolean inGame() {
        return state.inGame();
    }
    public static boolean paused() {
        return state.pause();
    }
}
