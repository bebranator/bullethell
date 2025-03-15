package bullethell.game.stagebad;

import bullethell.content.Bullets;
import bullethell.core.Client;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.type.Bullet;
import bullethell.game.Attack;
import bullethell.game.GameTime;
import bullethell.graphics.Shortcuts;
import bullethell.log.Log;
import com.badlogic.gdx.utils.Array;

import java.io.*;
import java.net.URISyntaxException;

// todo: remove
public class BadAppleWave extends Attack {
    private Array<Bullet> bullets;
    private Array<Page> pages;
    private final int targetFrames = 6572; // 6572
    public final int height = 48;
    public final int width = 64;
    private int currentFrame;
    private float currentFrameFloat;

    // todo: parse bin
    public BadAppleWave() throws IOException, URISyntaxException {
        bullets = new Array<>();
        pages = new Array<>();
        InputStream stream = Core.files.internal("bad_apple.bin").read(48 * targetFrames);

        for(int i = 0; i < targetFrames; i++) {
            try {
                pages.add(pageFromBin(stream, 0));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void update() {
        currentFrameFloat += GameTime.delta / 2;
        currentFrame = (int) currentFrameFloat;

        if(currentFrame >= targetFrames) return;

        setPage(pages.get(currentFrame));
    }

    public void setPage(Page page) {
//        for(int i = 0; i < width * height; i++) {
//            Bullet bul = bullets.get(i);
//            bul.enabled = page.bullets[i];
//        }

        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                Bullet bul = bullets.get((h * this.width) + w);
                bul.enabled = page.bullets[47 - h][w];
            }
        }
    }

    @Override
    public boolean isEnd() {
        return currentFrame + 1 >= targetFrames;
    }

    public final byte[] buf = new byte[8];

    public Page pageFromBin(InputStream binary, int position) throws IOException {
//        binary.skip(position * height);
        Page page = new Page();
        long buff;
        int pad;
        for(int h = 0; h < height; h++) {
            binary.read(buf);

            buff = bytesToLong(buf);

            pad = 65 - Long.toBinaryString(buff).length();
            for (int e = 0; e < pad; e++) {
                page.bullets[h][e] = false;
            }

            for(int i = 0; i < width; ++i) {
//                img.setRGB(i, h, (int) -((buff & (1L << i)) >> i));
                page.bullets[h][i] = ((buff & (1L << i)) >> i) == 1;
            }
        }
        return page;
    }

    public long bytesToLong(final byte[] b) {
        long target = 0;
        for (int i = 0; i < 8; i++) {
            target <<= 8;
            target |= (b[i] & 0xFF);
        }
        return target;
    }

    @Override
    public void end() {
        setPage(pages.get(0));
        bullets.forEach(Bullet::remove);
        bullets.clear();
        currentFrame = 0;
        currentFrameFloat = 0;
        Vars.arena.defaults();
        Log.info("ENDED");
    }

    @Override
    public void begin() {
        float r;
        float ow = Client.WIDTH;
        float oh = Client.HEIGHT; // original sizes (tw / th = ow / oh) !
        r = ow / width;

        Vars.sounds.playMusic(Core.audio.newMusic(Core.files.internal("music/bad_apple_extract.mp3")), false);
        Shortcuts.arenaNotification("BGM - Bad Apple!!!");

        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                int finalW = w;
                int finalH = h;
                bullets.add(
                    Bullet.spawn((e) -> {
                        e.lifetime = -1;
                        e.setSize(r / 2);
                        e.drawSize(r / 2);
                        e.enabled = false;
                        e.type = Bullets.blueSmall;

                        e.set(
                            r * finalW + r / 2, finalH * r + r / 2
                        );
                    })
                );
            }
        }
        setPage(pages.get(0));

//        Vars.arena.viewport.set(0, 0, ow + 1, oh);
//        Vars.arena.world.set(0, 0, ow, oh);
        Vars.arena.set(0, 0, ow + 1, oh);
    }

    @Override
    public String debug() {
        return "frame: " + currentFrame + "\n";
    }
}
