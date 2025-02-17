package bullethell.core;

import bullethell.entity.Arena;
import bullethell.entity.Entities;
import bullethell.entity.EntityGroup;
import bullethell.entity.type.*;
import bullethell.game.Ev;
import bullethell.game.GameState;
import bullethell.game.State;
import bullethell.game.dialog.DialogueManager;
import bullethell.log.Log;
import bullethell.module.*;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

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
    public static EntityGroup<HealthEntity> healthEntities; // they take damage and collide with player and player's bullets
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

        enemyBullets = entities.getGroup(Bullet.class);
        playerBullets = entities.getGroup(PlayerBullet.class);
        playerGroup = entities.getGroup(Player.class);
        healthEntities = entities.getGroup(HealthEntity.class);
        lasers = entities.getGroup(Laser.class);
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
