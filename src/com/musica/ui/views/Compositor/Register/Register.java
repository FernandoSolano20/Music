package com.musica.ui.views.Compositor.Register;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Register extends MusicUI {

        @FXML
        private TextField name;
        @FXML private TextField lastName;
        @FXML private ChoiceBox country;
        @FXML private TextField old;
        @FXML private TextField genders;
        @FXML private Button save;

        @FXML
        public void listarPaises(MouseEvent mouseEvent) throws IOException {
            ObservableList<String> countries = Stream.of(Locale.getISOCountries())
                    .map(locales -> new Locale("", locales))
                    .map(Locale::getDisplayCountry)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            country.setItems(countries);
        }

        @FXML
        protected void save(ActionEvent event) throws IOException {
            Window owner = save.getScene().getWindow();
            try {
                String name = this.name.getText();
                String lastName = this.lastName.getText();
                String country = this.country.getValue().toString();
                int old = Integer.parseInt(this.old.getText());

                String[] genders = this.genders.getText().split(",");

                boolean response = false;

                    response = controller.registerCompositor(name,lastName,country,old,genders);

                if (response == true){
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Compositor almacenado");
                    if(controller.userType() == "Administrador"){
                        super.rComp(event);
                    }
                    else {
                        Stage stage = (Stage) save.getScene().getWindow();
                        stage.close();
                    }
                }
                else {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El Compositor no se pudo almacenar");
                }
            } catch (Exception e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
            }
        }
    }

