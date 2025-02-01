package bullethell.module;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundControl {
    private Music currentPlaying;

    public SoundControl() {
    }

    public void playMusic(Music music, boolean loop) {
        if(currentPlaying != null) {
            currentPlaying.stop();
        }

        currentPlaying = music;
        currentPlaying.play();
        currentPlaying.setLooping(loop);
    }

    public void playSound(Sound sound) {
        sound.play();
    }
}
