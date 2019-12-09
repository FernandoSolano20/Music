package com.musica.ui.views.SongQueue;

import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

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
            do {
                if(fileLocation.hasNext()){
                    FileInputStream buff = new FileInputStream(fileLocation.next());
                    prehravac = new Player(buff);
                    prehravac.play();
                }
            } while (loop);
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
