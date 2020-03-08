package com.example.conecta4;

import android.content.Context;
import android.media.MediaPlayer;

public class Musica {
    private static MediaPlayer player;

    public static void play (Context context, int id){
        player = MediaPlayer.create(context, id);
        player.setLooping(true);
        player.start();
    }

    public static void stop (Context context){
        if(player != null){
            player.pause();
            player.release();
            player = null;
        }
    }

    public static void resume (Context context){
        player.release();
    }
}
