package com.musica.ui.views.Album.Update;

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
import java.time.LocalDate;

public class UpdateAlbum extends MusicUI {
    @FXML
    private TextField name;
    @FXML private TextField day;
    @FXML private TextField month;
    @FXML private TextField year;
    @FXML private Button image;
    @FXML private Button save;
    private int idAlbum;
    private String imagePath = "";
    private String pathImage = "";

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
            if(!pathImage.isEmpty()){
                pathImage = imagePath;
            }
            boolean response = false;

                response = controller.updateAlbum(idAlbum,name,release,pathImage);

            if (response == true){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Album almacenado");
                if(controller.userType() == "Administrador"){
                    super.rAlbum(event);
                }
                else {
                    Stage stage = (Stage) save.getScene().getWindow();
                    stage.close();
                }
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El album no se pudo almacenar");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }

    @Override
    public void transferId(String message) {
        try {
            super.transferId(message);
            String song[] = new String[0];

            song = controller.getAlbumById(Integer.parseInt(getId())).split(",");

            idAlbum = Integer.parseInt(song[0]);
            name.setText(song[1]);
            day.setText(song[2].split("-")[2]);
            month.setText(song[2].split("-")[1]);
            year.setText(song[2].split("-")[0]);
            imagePath = song[3];
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
}
