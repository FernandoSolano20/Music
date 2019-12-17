package com.musica.ui.views.ChangePass;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

public class ChangePass extends MusicUI {
    @FXML private TextField pass;
    @FXML
    protected void validate(ActionEvent event) throws IOException {
        Window owner = pass.getScene().getWindow();

        boolean response = false;
        try {
            response = controller.updateUser(pass.getText(),false);

            if(response){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Su usario ha sido verifcado y contraseña cambiada");
                super.index(event);
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La contraseña no se edito");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }
}
