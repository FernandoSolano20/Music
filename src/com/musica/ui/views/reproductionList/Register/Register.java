package com.musica.ui.views.reproductionList.Register;

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
    @FXML private TextField songs;
    @FXML private TextField year;
    @FXML private TextField month;
    @FXML private TextField day;
    @FXML private Button save;

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        //System.out.println(name.getText());
        String name = this.name.getText();
        int year = Integer.parseInt(this.year.getText());
        int month = Integer.parseInt(this.month.getText());
        int day = Integer.parseInt(this.day.getText());
        String[] songs = this.songs.getText().split(",");


        boolean response = controller.registerReproductionList(name,year,month,day,songs);
        if (response){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion almacenado");
            show.ShowWindow(event,"./views/login/login.fxml", "Iniciar Sesión");
        }
        else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion no se pudo almacenar");
        }
    }

}
