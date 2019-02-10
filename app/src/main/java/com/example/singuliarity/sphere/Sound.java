package com.example.singuliarity.sphere;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class Sound {
    MediaPlayer mp;
    Context ctx;
    Sound(int id_sound,Context ctx){
        this.ctx=ctx;
        mp=MediaPlayer.create(ctx,id_sound);
    }

    void play(){
        if(mp.isPlaying()){
            mp.stop();
        }
        mp.start();
    }
    void stopRelease(){
        mp.stop();
        mp.release();
    }
    void stop(){
        mp.stop();
    }

    void pause(){
        mp.pause();
    }

}
