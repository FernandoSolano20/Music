package com.musica.ui.views.Album.Register;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    protected void uploadImage(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\fersolano\\Desktop"));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();

        String name = this.name.getText();
        int day = Integer.parseInt(this.day.getText());
        int month = Integer.parseInt(this.month.getText());
        int year = Integer.parseInt(this.year.getText());
        LocalDate release = LocalDate.of(year,month,day);
        String image = "Image path"; //this.lastName.getText();
        String[] artists = this.artists.getText().split(",");

        boolean response = controller.registerAlbum(name,release,image,artists);
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
    }
}

