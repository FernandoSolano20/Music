package com.musica.ui.views.Compositor.Register;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


    public class Register extends MusicUI {

        @FXML
        private TextField name;
        @FXML private TextField lastName;
        @FXML private ChoiceBox country;
        @FXML private TextField old;
        @FXML private TextField genders;
        @FXML private Button save;

        @FXML
        protected void save(ActionEvent event) throws IOException {
            Window owner = save.getScene().getWindow();

            String name = this.name.getText();
            String lastName = this.lastName.getText();
            String country = "Country";//this.country.getText();
            int old = Integer.parseInt(this.old.getText());

            String[] genders = this.genders.getText().split(",");

            boolean response = controller.registerCompositor(name,lastName,country,old,genders);
            if (response == true){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Compositor almacenado");
                super.index(event);
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El Compositor no se pudo almacenar");
            }
        }
    }

