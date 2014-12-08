package com.gtotek.util;

import java.util.Random;

import com.gtotek.wordsearch.R;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayMusic {
	private MediaPlayer mMediaPlayer;
	
	private Context context;
	
	public PlayMusic(Context context) {
		this.context = context;
	}

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void play() {
        stop();
        
        int idx = new Random().nextInt(SoundConfig.arrSound.length);
		int sRandom = (SoundConfig.arrSound[idx]);
        
        mMediaPlayer = MediaPlayer.create(context, sRandom);
        mMediaPlayer.setVolume(0.4f, 0.4f);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
                
                play();
            }
        });

        mMediaPlayer.start();
    }

	public MediaPlayer getmMediaPlayer() {
		return mMediaPlayer;
	}

	public void setmMediaPlayer(MediaPlayer mMediaPlayer) {
		this.mMediaPlayer = mMediaPlayer;
	}
    
    
}
