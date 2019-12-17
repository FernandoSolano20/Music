package com.musica.ui.views.Song.Update;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class UpdateSong extends MusicUI {
    @FXML
    private TextField name;
    @FXML private TextField gender;
    @FXML private TextField artist;
    @FXML private TextField nameComp;
    @FXML private TextField year;
    @FXML private TextField month;
    @FXML private TextField day;
    @FXML private TextField album;
    @FXML private TextField score;
    @FXML private TextField price;
    @FXML private Button song;
    @FXML private Button save;
    int idSong;
    String songPath = "";
    String path = "";

    @FXML
    protected void uploadSong(ActionEvent event){
        Scene scene = save.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Song","*.mp3"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(!selectedFile.toPath().toString().isEmpty()){
            songPath = selectedFile.toPath().toString().replace("\\", "\\\\");
        }
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        try {
            //System.out.println(name.getText());
            String name = this.name.getText();
            String gender = this.gender.getText();
            String artist = this.artist.getText();
            String nameComp = this.nameComp.getText();
            int year = Integer.parseInt(this.year.getText());
            int month = Integer.parseInt(this.month.getText());
            int day = Integer.parseInt(this.day.getText());
            String album = this.album.getText();
            int price = Integer.parseInt(this.price.getText());
            int score = Integer.parseInt(this.score.getText());
            if(songPath.isEmpty()){
                songPath = path;
            }

            if(score > 5 || score < 0){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion puntacion no valida");
                return;
            }

            int response = 0;

                response = controller.updateSong(idSong,name,gender,artist,nameComp,year,month,day,album,score,songPath,price);

            if (response == 1){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion almacenado");
                if(controller.userType() == "Administrador"){
                    super.rSongAdmin(event);
                }
                else {
                    Stage stage = (Stage) save.getScene().getWindow();
                    stage.close();
                }
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion no se pudo almacenar");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }

    @Override
    public void transferId(String message) {
        super.transferId(message);
        String song[] = new String[0];
        try {
            song = controller.getSongById(Integer.parseInt(getId())).split(",");

            idSong = Integer.parseInt(song[0]);
            name.setText(song[1]);
            gender.setText(song[3]);
            artist.setText(song[17]);
            nameComp.setText(song[19] + " " + song[20]);
            year.setText(song[23].split("-")[0]);
            month.setText(song[23].split("-")[1]);
            day.setText(song[23].split("-")[2]);
            album.setText(song[25]);
            score.setText(song[28]);
            price.setText(song[30]);
            path = song[29];
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
}
