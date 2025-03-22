package com.example.mathcalculator;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class VibrationManager {
    private Vibrator vibrator;

    public VibrationManager(Context context){
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void vibrateSuccess(){
        long[] pattern = {0, 200};
        int[] amplitudes = {0, 255};
        vibrate(pattern, amplitudes, -1);
    }

    public void vibrateError(){
        long[] pattern = {0, 100, 100, 100};
        int[] amplitudes = {0, 255, 0, 255};
        vibrate(pattern, amplitudes, -1);
    }

    private void vibrate(long[] pattern, int[] amplitudes, int repeat){
        if (vibrator != null && vibrator.hasVibrator()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                VibrationEffect vibrationEffect = VibrationEffect.createWaveform(pattern, amplitudes, repeat);
                vibrator.vibrate(vibrationEffect);
            } else {
                vibrator.vibrate(pattern, repeat);
            }
        }
    }
}
