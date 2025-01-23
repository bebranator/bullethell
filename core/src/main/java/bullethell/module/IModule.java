package bullethell.module;

public interface IModule {
    default void render() {}
    default void create() {}
    default void resize(int width, int height) {}
    default void dispose() {}
    default void pause() {}
    default void resume() {}
}
