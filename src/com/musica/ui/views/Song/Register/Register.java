package com.musica.ui.views.Song.Register;

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

public class Register extends MusicUI {
    @FXML private TextField name;
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
    String songPath = "";

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
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion debe subirse");
                return;
            }

            if(score > 5 || score < 0){
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion puntacion no valida");
                return;
            }

            int response = 0;
            response = controller.registerSong(name,gender,artist,nameComp,year,month,day,album,score,songPath,price);

            if (response > 0){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion almacenado");
                if(controller.userType() == "Administrador"){
                    super.rSongAdmin(event);
                }
                else {
                    controller.buy(response);
                    super.buy(event);
                }
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion no se pudo almacenar");
                if(response == -1){
                    super.cCompOnSong(event);
                }
                else if(response == -2){
                    super.cArtOnSong(event);
                }
                else if(response == -3){
                    super.cGenderOnSong(event);
                }
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }

}
