package com.musica.ui.views.registerSong;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class Register extends MusicUI {
    @FXML private TextField name;
    @FXML private TextField gender;
    @FXML private TextField artist;
    @FXML private TextField nameComp;
    @FXML private TextField lastNameComp;
    @FXML private TextField year;
    @FXML private TextField month;
    @FXML private TextField day;
    @FXML private TextField album;
    @FXML private TextField score;
    @FXML private Button song;
    @FXML private Button save;

    @FXML
    protected void uploadSong(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\fersolano\\Desktop"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.mp3"));
        File selectedFile = fileChooser.showOpenDialog(null);
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        //System.out.println(name.getText());
        String name = this.name.getText();
        String gender = this.gender.getText();
        String artist = this.artist.getText();
        String nameComp = this.nameComp.getText();
        String lastNameComp = this.lastNameComp.getText();
        int year = Integer.parseInt(this.year.getText());
        int month = Integer.parseInt(this.month.getText());
        int day = Integer.parseInt(this.day.getText());
        String album = this.album.getText();
        int score = Integer.parseInt(this.score.getText());
        String song = "Path";

        int response = controller.registerSong(name,gender,artist,nameComp,lastNameComp,year,month,day,album,score,song);
        if (response == 1){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion almacenado");
            show.ShowWindow(event,"./views/login/login.fxml", "Iniciar Sesión");
        }
        else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion no se pudo almacenar");
        }
    }

}
