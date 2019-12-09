package com.musica.ui.views.reproductionList.Lists.Song;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;

public class AddSongReproduction extends MusicUI {
    @FXML
    private TextField name;
    @FXML private Button save;

    public void transferId(String message) {
        super.transferId(message);
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();
        //System.out.println(name.getText());
        String name = this.name.getText();


        boolean response = controller.saveSongReproductionList(Integer.parseInt(super.getId()), name);
        if (response){
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Cancion agregada");
            super.adminSongsReproductionList(event,""+super.getId());
        }
        else {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "La cancion no se pudo almacenar");
        }
    }
}
