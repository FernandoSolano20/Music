package com.musica.ui.views.Artist.Register;

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

    @FXML private TextField name;
    @FXML private TextField lastName;
    @FXML private TextField dayBorn;
    @FXML private TextField monthBorn;
    @FXML private TextField yearBorn;
    @FXML private ChoiceBox country;
    @FXML private TextField artistName;
    @FXML private TextField dayDead;
    @FXML private TextField monthDead;
    @FXML private TextField yearDead;
    @FXML private TextField reference;
    @FXML private TextField description;
    @FXML private TextField gender;
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
            int dayBorn = Integer.parseInt(this.dayBorn.getText());
            int monthBorn = Integer.parseInt(this.monthBorn.getText());
            int yearBorn = Integer.parseInt(this.yearBorn.getText());
            LocalDate dateBorn = LocalDate.of(yearBorn,monthBorn,dayBorn);
            String country = this.country.getValue().toString();
            String artistName = this.artistName.getText();
            String dayDead = this.dayDead.getText();
            String monthDead = this.monthDead.getText();
            String yearDead = this.yearDead.getText();
            LocalDate dateDead = null;
            if(!dayDead.isEmpty() && !monthDead.isEmpty() && !yearDead.isEmpty()){
                dateDead = LocalDate.of(Integer.parseInt(yearDead),Integer.parseInt(monthDead),Integer.parseInt(dayDead));
            }
            String reference = this.reference.getText();
            String description = this.description.getText();
            String gender = this.gender.getText();

            boolean response = false;

                response = controller.registerArtist(name,lastName,country,dateBorn,dateDead,reference,description,gender,artistName);

            if (response == true){
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Exitoso", "Artisita almacenado");
                if(controller.userType() == "Administrador"){
                    super.rArt(event);
                }
                else {
                    Stage stage = (Stage) save.getScene().getWindow();
                    stage.close();
                }
            }
            else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "El Artisita no se pudo almacenar");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", e.getMessage());
        }
    }
}
