package com.musica.ui.views.reproductionList.Update;

import com.musica.ui.AlertHelper;
import com.musica.ui.ButtonCell;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

public class UpdateRL extends MusicUI {
    @FXML
    private TextField name;
    @FXML
    private Button save;

    @Override
    public void transferId(String message) {
        super.transferId(message);
        String[] list = new String[0];
        try {
            list = controller.searchReproductionListById(Integer.parseInt(getId())).split(",");
            name.setText(list[2]);
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    public void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        try {
            String name = this.name.getText();
            boolean response = false;
            response = controller.updateReproductionListId(Integer.parseInt(getId()), name);

            if (response){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion almacenado");
                super.lists(event);
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion no se pudo almacenar");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }
}
