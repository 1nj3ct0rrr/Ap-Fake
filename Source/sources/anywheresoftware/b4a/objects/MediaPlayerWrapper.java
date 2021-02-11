package anywheresoftware.b4a.objects;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.streams.File;
import java.io.FileInputStream;
import java.io.IOException;

@BA.ShortName("MediaPlayer")
public class MediaPlayerWrapper {
    protected String eventName;
    @BA.Hide
    public MediaPlayer mp;

    public void Initialize() throws IllegalArgumentException, IllegalStateException, IOException {
        this.mp = new MediaPlayer();
    }

    public void Initialize2(final BA ba, String EventName) throws IllegalArgumentException, IllegalStateException, IOException {
        Initialize();
        this.eventName = EventName.toLowerCase(BA.cul);
        this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            /* class anywheresoftware.b4a.objects.MediaPlayerWrapper.AnonymousClass1 */

            public void onCompletion(MediaPlayer mp) {
                ba.raiseEvent(MediaPlayerWrapper.this, String.valueOf(MediaPlayerWrapper.this.eventName) + "_complete", new Object[0]);
            }
        });
    }

    public void Load(String Dir, String FileName) throws IllegalArgumentException, IllegalStateException, IOException {
        this.mp.reset();
        loadAfterReset(Dir, FileName);
    }

    private void loadAfterReset(String Dir, String FileName) throws IllegalArgumentException, IllegalStateException, IOException {
        if (Dir.equals(File.getDirAssets())) {
            if (File.virtualAssetsFolder != null) {
                loadAfterReset(File.virtualAssetsFolder, File.getUnpackedVirtualAssetFile(FileName));
                return;
            }
            AssetFileDescriptor fd = BA.applicationContext.getAssets().openFd(FileName.toLowerCase(BA.cul));
            if (fd.getDeclaredLength() < 0) {
                this.mp.setDataSource(fd.getFileDescriptor());
            } else {
                this.mp.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getDeclaredLength());
            }
        } else if (Dir.startsWith(File.getDirInternal()) || Dir.equals(File.getDirInternalCache())) {
            this.mp.setDataSource(new FileInputStream(new java.io.File(Dir, FileName)).getFD());
        } else if (Dir.equals(File.ContentDir)) {
            AssetFileDescriptor fd2 = BA.applicationContext.getContentResolver().openAssetFileDescriptor(Uri.parse(FileName), "r");
            if (fd2.getDeclaredLength() < 0) {
                this.mp.setDataSource(fd2.getFileDescriptor());
            } else {
                this.mp.setDataSource(fd2.getFileDescriptor(), fd2.getStartOffset(), fd2.getDeclaredLength());
            }
        } else {
            this.mp.setDataSource(new java.io.File(Dir, FileName).toString());
        }
        this.mp.prepare();
    }

    public boolean getLooping() {
        return this.mp.isLooping();
    }

    public void setLooping(boolean value) {
        this.mp.setLooping(value);
    }

    public void Play() {
        this.mp.start();
    }

    public void Stop() {
        this.mp.reset();
    }

    public void Pause() {
        this.mp.pause();
    }

    public int getDuration() {
        return this.mp.getDuration();
    }

    public int getPosition() {
        return this.mp.getCurrentPosition();
    }

    public void setPosition(int value) {
        this.mp.seekTo(value);
    }

    public void SetVolume(float Left, float Right) {
        this.mp.setVolume(Left, Right);
    }

    public boolean IsPlaying() {
        return this.mp.isPlaying();
    }

    public void Release() {
        this.mp.release();
    }
}
