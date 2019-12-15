package com.musica.ui.views.Artist.Register;

import com.musica.ui.AlertHelper;
import com.musica.ui.MusicUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;


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
    protected void save(ActionEvent event) throws IOException {
        Window owner = save.getScene().getWindow();

        String name = this.name.getText();
        String lastName = this.lastName.getText();
        int dayBorn = Integer.parseInt(this.dayBorn.getText());
        int monthBorn = Integer.parseInt(this.monthBorn.getText());
        int yearBorn = Integer.parseInt(this.yearBorn.getText());
        LocalDate dateBorn = LocalDate.of(yearBorn,monthBorn,dayBorn);
        String country = "Country";//this.country.getText();
        String artistName = this.artistName.getText();
        int dayDead = Integer.parseInt(this.dayDead.getText());
        int monthDead = Integer.parseInt(this.monthDead.getText());
        int yearDead = Integer.parseInt(this.yearDead.getText());
        LocalDate dateDead = null;
        if(dayDead != 0 && monthDead != 0 && yearDead != 0){
            dateDead = LocalDate.of(yearDead,monthDead,dayDead);
        }
        String reference = this.reference.getText();
        String description = this.description.getText();
        String gender = this.gender.getText();

        boolean response = controller.registerArtist(name,lastName,country,dateBorn,dateDead,reference,description,gender,artistName);
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
    }
}
