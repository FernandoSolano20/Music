package com.musica.ui.views.Compositor.Update;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

public class UpdateCompositor extends MusicUI {
    @FXML
    private TextField name;
    @FXML private TextField lastName;
    @FXML private ChoiceBox country;
    @FXML private TextField old;
    @FXML private Button save;
    private int idComp;

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();

        String name = this.name.getText();
        String lastName = this.lastName.getText();
        String country = "Country";//this.country.getText();
        int old = Integer.parseInt(this.old.getText());

        boolean response = controller.updateCompositor(idComp,name,lastName,country,old);
        if (response == true){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Compositor almacenado");
            super.rComp(event);
        }
        else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El Compositor no se pudo almacenar");
        }
    }

    @Override
    public void transferId(String message) {
        super.transferId(message);
        String song[] = controller.getCompositorById(Integer.parseInt(getId())).split(",");
        idComp = Integer.parseInt(song[0]);
        name.setText(song[1]);
        lastName.setText(song[2]);
        country.setValue(song[3]);
        old.setText(song[4]);
    }
}
