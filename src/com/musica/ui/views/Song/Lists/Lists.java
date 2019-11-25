package com.musica.ui.views.Song.Lists;

import com.musica.bl.Song.Song;
import com.musica.ui.MusicUI;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;

public class Lists extends MusicUI {
    @FXML
    private TextField display;

    //Receive message from scene 1
    public void transferId(String message) {
        super.transferId(message);
        List<Song> songs = controller.searchSongsByReproductionListId(Integer.parseInt(getId()));
    }
}
