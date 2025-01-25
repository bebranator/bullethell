package bullethell.module;

import com.badlogic.gdx.ApplicationListener;
import com.github.czyzby.kiwi.util.gdx.InputAwareApplicationListener;

public abstract class AbstractCore implements ApplicationListener {
    protected IModule[] modules = {};

    public void add(IModule module){
        //use an array instead of a seq/list, for faster iteration; modules do not get added often, so a resize each time is acceptable
        IModule[] news = new IModule[modules.length + 1];
        news[news.length - 1] = module;
        System.arraycopy(modules, 0, news, 0, modules.length);
        modules = news;
    }

    public void remove(int index) {
        IModule[] news = new IModule[modules.length - 1];
        modules[index].dispose();
        System.arraycopy(modules, 0, news, 0, modules.length);
        modules = news;
    }

    public abstract void setup();

    @Override
    public void create() {
        setup();
        for(IModule module : modules) module.create();
    }

    @Override
    public void resize(int width, int height) {
        for(IModule module : modules) module.resize(width, height);
    }

    @Override
    public void render() {
        for(IModule module : modules) module.render();
    }

    @Override
    public void pause() {
        for(IModule module : modules) module.pause();
    }

    @Override
    public void resume() {
        for(IModule module : modules) module.resume();
    }

    @Override
    public void dispose() {
        for(IModule module : modules) module.dispose();
    }
}
