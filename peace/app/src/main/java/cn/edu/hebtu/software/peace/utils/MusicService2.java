package cn.edu.hebtu.software.peace.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cn.edu.hebtu.software.peace.R;

public class MusicService2 extends Service {
    private MediaPlayer mediaPlayer;

    private IBinder binder=new PlayerBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.xiayu);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }


    public class PlayerBinder extends Binder {
        //暂停
        public void pause() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.start();
            }
        }
        //继续
        public void restart(){
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        }
    }

}
