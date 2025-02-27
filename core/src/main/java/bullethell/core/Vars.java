package bullethell.core;

import bullethell.entity.Arena;
import bullethell.entity.Entities;
import bullethell.entity.EntityGroup;
import bullethell.entity.type.*;
import bullethell.game.Ev;
import bullethell.game.GameState;
import bullethell.game.State;
import bullethell.game.dialog.DialogueManager;
import bullethell.module.*;

public class Vars {
    private static State state;
    public static UI ui;
    public static Renderer renderer;
    public static Control control;
    public static SoundControl sounds;

    public static Entities entities;
    public static Arena arena;
    public static GameState game;

    public static EntityGroup<Bullet> enemyBullets;
    public static EntityGroup<PlayerBullet> playerBullets;
    // i want to merge them
    public static EntityGroup<Player> playerGroup;
    public static EntityGroup<BossEntity> bossGroup;
    public static EntityGroup<Laser> lasers;
    public static EntityGroup<EnemyEntity> enemies; // they take damage and collide with player and player's bullets
    // why we do other group for player lmao
    public static Player player;

    public static DialogueManager dialogue;

    public static void init() {
        setState(State.menu);
        entities = new Entities();
        arena = new Arena();
        game = new GameState();
        sounds = new SoundControl();

        dialogue = new DialogueManager();

        enemyBullets = entities.getGroup(Bullet.class, true);
        playerBullets = entities.getGroup(PlayerBullet.class, true);
        playerGroup = entities.getGroup(Player.class, true);
        enemies = entities.getGroup(EnemyEntity.class, true);
        lasers = entities.getGroup(Laser.class, true);
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

    public static State state() {
        return state;
    }
}
