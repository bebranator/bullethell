package bullethell.game.stagebad;

import bullethell.content.Bullets;
import bullethell.core.Client;
import bullethell.core.Core;
import bullethell.core.Vars;
import bullethell.entity.trait.Entityc;
import bullethell.entity.type.Bullet;
import bullethell.game.Attack;
import bullethell.game.GameTime;
import bullethell.log.Log;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BadAppleWave extends Attack {
    private Array<Bullet> bullets;
    private Array<Page> pages;
    private final int targetFrames = 6572;
    public final int height = 48;
    public final int width = 64;
    private final int targetSize = width * height;
    private int currentFrame;
    private float currentFrameFloat;

    // todo: parse bin
    public BadAppleWave() {
        bullets = new Array<>();
        pages = new Array<>();
        float r;
        float ow = Client.WIDTH;
        float oh = Client.HEIGHT; // original sizes (tw / th = ow / oh) !
        float tw = width, th = height; // target sizes
        r = ow / tw;

        for(int h = 0; h < height; h++) {
            for(int w = 0; w < width; w++) {
                bullets.add(
                    Bullet.spawn((e) -> {
                        e.lifetime = -1;
                        e.velocity().setZero();
                        e.setSize(r / 2);
                        e.drawSize = r / 2;
                    }, Bullets.blueSmall, r + w * r + r / 2, (th * r) - h * r + r / 2 - r)
                );
            }
        }
        // bone
        bullets.add(Bullet.spawn((e) -> {
            e.lifetime = -1;
            e.velocity().setZero();
            e.setSize(1);
            e.drawSize = 1;
        }, Bullets.blueSmall, -10, -10));

        Vars.arena.viewport.set(0, 0, ow, oh);
        Vars.arena.world.set(0, 0, ow, oh);

        // parse pages
        FileInputStream stream;
        try {
            // it should always succeed
            stream = new FileInputStream("assets/bad_apple.bin");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

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
                Bullet bul = bullets.get(h * 64 + w);
                bul.enabled = page.bullets[h][w];
            }
        }
    }

    @Override
    public boolean isEnd() {
        return currentFrame + 1 >= targetFrames;
    }

    public final byte[] buf = new byte[8];

    public Page pageFromBin(FileInputStream binary, int position) throws IOException {
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
        bullets.forEach(Entityc::remove);
    }
}
