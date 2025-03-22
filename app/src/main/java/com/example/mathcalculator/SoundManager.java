package com.example.mathcalculator;

import android.content.Context;
import android.media.SoundPool;
import android.os.Build;

public class SoundManager {
    private Context context;
    private SoundPool soundPool;
    private int successSoundId, errorSoundId, clickSoundId;

    public SoundManager(Context context){
        this.context = context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            soundPool = new SoundPool.Builder().setMaxStreams(3).build();
        } else{
            soundPool = new SoundPool(3, android.media.AudioManager.STREAM_MUSIC,0);
        }

        clickSoundId = soundPool.load(context, R.raw.click, 1);
        successSoundId = soundPool.load(context,R.raw.success,1);
        errorSoundId = soundPool.load(context, R.raw.error,1);
    }

    public void playClickSound(){
        soundPool.play(clickSoundId, 1.0f,1.0f,1,0,1.0f);
    }

    public void playSuccessSound(){
        soundPool.play(successSoundId, 1.0f,1.0f,1,0,1.0f);
    }

    public void playErrorSound(){
        soundPool.play(errorSoundId, 1.0f,1.0f,1,0,1.0f);
    }

    public void release(){
        if(soundPool != null){
            soundPool.release();
            soundPool = null;
        }
    }

}
