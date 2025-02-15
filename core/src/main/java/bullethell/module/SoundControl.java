package bullethell.module;

import bullethell.content.Sounds;
import bullethell.core.Events;
import bullethell.game.Ev;
import bullethell.game.State;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundControl {
    private Music currentPlaying;

    public SoundControl() {
        Events.on(Ev.StateChange.class, e -> {
            if(e.current.pause()) pauseMusic();

            if(e.current.inGame()) resumeMusic();
        });
    }

    public void playMusic(Music music, boolean loop) {
        if(currentPlaying != null) {
            currentPlaying.stop();
        }

        currentPlaying = music;
        currentPlaying.play();
        currentPlaying.setLooping(loop);
    }
    public void resumeMusic() {
        if(currentPlaying != null) currentPlaying.play();
    }

    public void pauseMusic() {
        if(currentPlaying != null) currentPlaying.pause();
    }

    public void stopMusic() {
        if(currentPlaying == null) return;

        currentPlaying.stop();
        currentPlaying = null;
    }

    public void playSound(Sound sound) {
        sound.play();
    }
    public void playSound(Sound sound, float vol) {
        sound.play(vol);
    }
    public void ok() {
        Sounds.ok.play(.5f);
    }
    public void option() {
        Sounds.select.play(.5f);
    }
}
