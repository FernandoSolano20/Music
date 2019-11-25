package com.musica.ui.views.Gender.Register;

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
    @FXML private TextField description;
    @FXML private Button save;

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();

        String name = this.name.getText();
        String description = this.description.getText();

        boolean response = controller.registerGender(name,description);
        if (response == true){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Genero almacenado");
            super.index(event);
        }
        else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El genero no se pudo almacenar");
        }
    }

}
