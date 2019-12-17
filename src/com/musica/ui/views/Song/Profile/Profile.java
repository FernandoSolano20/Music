package com.musica.ui.views.Song.Profile;

import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Profile extends MusicUI {
    @FXML private Label name;
    @FXML private Label gender;
    @FXML private Label album;
    @FXML private Label comp;
    @FXML private Label art;
    @FXML private Label score;
    @FXML private Label release;
    @FXML private ImageView imageAlbum;

    @FXML
    protected void index(ActionEvent event) throws IOException {
        super.index(event);
    }

    @FXML
    protected void buy(ActionEvent event) throws IOException {
        super.buy(event);
    }

    @FXML
    protected void lists(ActionEvent event) throws IOException {
        super.lists(event);
    }

    @FXML
    protected void catalogs(ActionEvent event) throws IOException {
        super.catalogs(event);
    }

    @FXML
    protected void albums(ActionEvent event) throws IOException {
        super.albums(event);
    }

    @FXML
    protected void reproductions(ActionEvent event) throws IOException {
        super.reproductions(event);
    }

    @Override
    public void transferId(String message) {
        super.transferId(message);
        String song[] = controller.getSongById(Integer.parseInt(getId())).split(",");
        name.setText(song[1]);
        gender.setText(song[3]);
        album.setText(song[25]);
        comp.setText(song[19] + " " + song[20]);
        art.setText(song[17] + " (" + song[6] + " " + song[7] + ")");
        score.setText(song[28]);
        release.setText(song[23]);
        showImage(song);
    }

    private void showImage(String[] song){
        File file = new File(song[27]);
        byte[] btImagen = new byte[0];
        try {
            btImagen = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream imgByteArray = new ByteArrayInputStream(btImagen);
        Image img = new Image(imgByteArray, 199, 199, false, false);;
        imageAlbum.setImage(img);
    }
}
