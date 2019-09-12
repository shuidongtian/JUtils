package com.jm.jutils;

import android.content.Context;
import android.media.AudioManager;

public class VoiceUtils {



    /**
     *@Description:设置音量 0-100
     *@Params:
     */

    public static void setVolume(Context context, int volume) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volumef1 = volume / 100f;
        float volumef2 = maxVolume * volumef1;
        volume = (int) volumef2;
        if (volumef2 > maxVolume) {
            volume = maxVolume;
        } else if (volume <= 0) {
            volume = 0;
        }
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_PLAY_SOUND);
    }

    /**
     *@Description:获取当前音量
     *@Params:
     */

    public static int getVolume(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        return currentVolume;
         }
}
