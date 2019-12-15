package com.musica.ui.views.Gender.Update;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateGender extends MusicUI {
    @FXML private TextField name;
    @FXML private TextField description;
    @FXML private Button save;
    private int idGender;

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();

        String name = this.name.getText();
        String description = this.description.getText();

        boolean response = controller.updateGender(idGender,name,description);
        if (response == true){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Genero almacenado");
            super.rGender(event);
        }
        else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El genero no se pudo almacenar");
        }
    }

    @Override
    public void transferId(String message) {
        super.transferId(message);
        String song[] = controller.getGenderById(Integer.parseInt(getId())).split(",");
        idGender = Integer.parseInt(song[0]);
        name.setText(song[1]);
        description.setText(song[2]);
    }
}
