package com.musica.ui.views.Album.Register;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


public class Register extends MusicUI {

    @FXML private TextField name;
    @FXML private TextField day;
    @FXML private TextField month;
    @FXML private TextField year;
    @FXML private TextField artists;
    @FXML private Button image;
    @FXML private Button save;
    private String pathImage = "";
    String songPath = "";

    @FXML private TextField nameSong;
    @FXML private TextField gender;
    @FXML private TextField art;
    @FXML private TextField comp;
    @FXML private TextField yearSong;
    @FXML private TextField monthSong;
    @FXML private TextField daySong;
    @FXML private TextField score;
    @FXML private TextField price;

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
    protected void uploadImage(ActionEvent event){
        Scene scene = save.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(!selectedFile.toPath().toString().isEmpty()){
            pathImage = selectedFile.toPath().toString().replace("\\", "\\\\");
        }
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        try {
            String name = this.name.getText();
            int day = Integer.parseInt(this.day.getText());
            int month = Integer.parseInt(this.month.getText());
            int year = Integer.parseInt(this.year.getText());
            LocalDate release = LocalDate.of(year,month,day);
            String[] artists = this.artists.getText().split(",");

            String nameSong = this.nameSong.getText();
            String gender = this.gender.getText();
            String art = this.art.getText();
            String comp = this.comp.getText();
            int yearSong = Integer.parseInt(this.yearSong.getText());
            int monthSong = Integer.parseInt(this.monthSong.getText());
            int daySong = Integer.parseInt(this.daySong.getText());
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

            boolean response = false;

            response = controller.registerAlbum(name,release,pathImage,artists,nameSong,gender,art,comp,yearSong,monthSong,daySong,price,score,songPath);

            if (response == true){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Album almacenado");
                if(controller.userType() == "Administrador"){
                    super.rAlbum(event);
                }
                else {
                    super.albums(event);
                }
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El album no se pudo almacenar");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }
}

