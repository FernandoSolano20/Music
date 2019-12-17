package com.musica.ui.views.SongQueue;

import com.musica.ui.MusicUI;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.util.Iterator;

public class MyAudioPlayer extends Thread {

    private Iterator<String> fileLocation;
    private boolean loop;
    private Player prehravac;

    public MyAudioPlayer(Iterator fileLocation, boolean loop) {
        this.fileLocation = fileLocation;
        this.loop = loop;
    }

    public void run() {

        try {
            int i = 0;
            do {
                if(fileLocation.hasNext()){
                    if(MusicUI.getQueue().get(i) != null){
                        FileInputStream buff = new FileInputStream(MusicUI.getQueue().get(i));
                        prehravac = new Player(buff);
                        MusicUI.getQueue().remove(i);
                        prehravac.play();
                        i++;
                    }
                }
                else {
                    loop = false;
                }
            } while (loop);
            close();
        } catch (Exception ioe) {
            System.out.println(ioe);
            // TODO error handling
        }
    }

    public void close(){
        loop = false;
        prehravac.close();
        this.interrupt();
    }
}
